package it.univpm.hhc.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.inject.New;

import it.univpm.hhc.utils.LocalDateToDateConverter;

import org.hamcrest.number.IsNaN;
import org.hibernate.mapping.Set;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Purchase;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.dao.UserDetailsDao;
import it.univpm.hhc.model.dao.UserDetailsDaoDefault;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;
import it.univpm.hhc.services.ItemService;
import it.univpm.hhc.services.PurchaseService;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserService;
import it.univpm.hhc.services.AddressService;
import net.bytebuddy.asm.Advice.This;

@RequestMapping("/users")
@Controller
public class UserController {
	
	private PublicController PC;
	private UserService userService;
	private SubService subService;
	private AddressService addressService;
	private ItemService itemService;
	private PurchaseService purchaseService;



	private CartService cartService;
	private CartItemService cartItemService;
	
	private UserDetailsDao userDao;
	
	private PasswordEncoder pe = new BCryptPasswordEncoder();
	

	public User getCurrentUser()
	{
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			User logged_user = userService.findByEmail(username);
			return logged_user;
			
		}else {
			String username = principal.toString();
			return null;
		}
			
	}	
	
	//USER///////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping(value="/edit")//occhio devo gestire la modifica a cascata
	public String edit(Model uiModel) {
		User logged_user = getCurrentUser();
		uiModel.addAttribute("user", logged_user);
		
		return "users/userform";
	}
	
	@GetMapping(value="/myprofile")
	public String myprofile(Model uiModel) {
		User logged_user = getCurrentUser();
		uiModel.addAttribute("user", logged_user);
		return "users/myprofile";
	}
	
	@GetMapping(value="/deletemyaccount")
	public String deletemyaccount() {
		return "users/deleteacccheck";
	}
	
	@RequestMapping(value="/checkanddelete", method=RequestMethod.POST)
	public String checkanddelete(@RequestParam String confirmation, Model uiModel){
		User logged_user = getCurrentUser();
		if(!confirmation.equals("CONFERMO")) {
			String errorMessage = "Non hai inserito correttamente il messaggio!";
			uiModel.addAttribute("errorMessage", errorMessage);
			return"users/deleteacccheck";
		} else if(confirmation.equals("CONFERMO")) {
			logged_user.setActive(false);
			userService.update(logged_user);
			return "redirect:/logout";
		}else {
			String errorMessage = "Oops, qualcosa è andato storto, riprova!";
			uiModel.addAttribute("errorMessage", errorMessage);
			return"users/deleteacccheck";
		}
	}
	
	@GetMapping(value = "/{userId}/delete")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("userId") String userId) {
		this.userService.delete(new Long(userId));//anziché eliminare dovrà disabilitare (DA CAMBIARE)
		//QUI DA RICHIAMARE IL LOGOUT
		return "redirect:/";
	}
	
	@GetMapping(value="/editpass")
	public String editpass(Model uiModel) {
		User loggedUser = getCurrentUser();
		uiModel.addAttribute("pass", loggedUser.getPassword());
		return "users/editpass";
	}

	
	@PostMapping(value = "/save")
	public String saveUser(@ModelAttribute("newUser") User newUser, BindingResult br) {
		
		this.userService.update(newUser);
		
		return "redirect:/users/edit";
		
	}
	
	@RequestMapping(value = "/savepass", method=RequestMethod.POST)
	public String saveUserpass(@RequestParam String password) {
		this.userService.update(getCurrentUser(), password);
		return "redirect:/users/edit";		
	}
	
//CART//////////////////////////////////////////////////////////////////////////////////////
	
	//Ritorna correttamente gli  item nel carrello dell'utente "loggato", da implementare il get dell'id carrello associato all'utente in maniera dinamica
	@GetMapping(value = "/cartlist")
	public String list( Model uiModel) {
		double total = 0;
		Long cartLong= getCurrentUser().getCarts().getCart_id();
		List<Cart_item> allItem = this.cartItemService.findByCart(cartLong);
		uiModel.addAttribute("items", allItem);  //quello che restituisco alla vista
		int item_number = allItem.size();
		
		for (Cart_item i : allItem){
			if(i.getQuantity() >= 1) {
				item_number += i.getQuantity()-1;
				total += i.getQuantity() * (i.getItem().getPrice());
			}
		}

		uiModel.addAttribute("total", total);
		uiModel.addAttribute("item_number", item_number);
		return "users/cartlist";
	}
	
	//Ok, funzionante
	@GetMapping(value = "/{cart_item_id}/cartdelete")
	public String delete(@PathVariable("cart_item_id") Long cart_item_id) {
		Cart_item item = cartItemService.findByid(cart_item_id); //ottengo un'istanza del cart_item in questione
		this.cartItemService.delete(cart_item_id); 
		Cart cart = item.getCart();	//ottengo il carrello associato per poi ricavarmi l'id da passare alla vista
		Long cart_id = cart.getCart_id();
		return "redirect:/users/cartlist";
	}
	

	@PostMapping(value = "/editcart")
	public String editcart(@RequestParam("cart_item_id") Long cart_item_id, @RequestParam("quantity") int quantity, Model uiModel) {
		String errorMessage;
		
		Cart_item i = this.cartItemService.findByid(cart_item_id);
		if(i.getQuantity()<=0) 
			errorMessage="Valore non consentito";
		else {	
			Cart_item newCartItem=cartItemService.findByid(cart_item_id);
			newCartItem.setQuantity(quantity);
			this.cartItemService.update(newCartItem);
		}
		return "redirect:/users/cartlist";
	}

	
	@PostMapping(value = "/cartsave")
	public String save(@ModelAttribute("newCartItem") Cart_item newCartItem,BindingResult br) {
		Long cartLong= getCurrentUser().getCarts().getCart_id();
		Cart c = cartService.findById(cartLong);
		newCartItem.setCart(c);
		this.cartItemService.update(newCartItem);
		
		return "redirect:/users/cartlist";
	}

	
//////////ITEM//////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping(value = "/{itemId}/viewitemdetail")
	public String viewitemdetail(@PathVariable("itemId") long itemId, Model model)
		{
			String errorMessage = "Articolo non trovato";
	      
	        model.addAttribute("errorMessage", errorMessage);
	        model.addAttribute("item", this.itemService.findById(itemId));

			return "users/viewitem";	

	    }
	

	@PostMapping(value = "/addtocart")
	public String addtocart(@RequestParam(value="itemId") String itemId,@ModelAttribute("newCartItem") Cart_item newCartItem, @ModelAttribute("newItem") String newItem) {
		Cart c = cartService.findByUserId(getCurrentUser().getUser_id());

		
		newCartItem.setCart(c);
		Item item= itemService.findById(Long.parseLong(itemId));
		newCartItem.setItem(item);
		this.cartItemService.update(newCartItem);

		return "redirect:/itemlist";
	}
	

	
	
	
	
	
	//SUB//////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping(value = "/view_sub")
    public String viwesub(@RequestParam(value = "error", required = false) String error, Model model) {
		
		String errorMessage = "Non hai ancora un abbonamento, affrettati!";
      
        User user=getCurrentUser();
        if(user.getSub()==null)
            model.addAttribute("sub", null);
        else {
        	model.addAttribute("sub", this.subService.findById(user.getSub().getSub_id()));
		}
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("user", this.getCurrentUser());
       
		return "users/my_sub";	

    }
	
	@GetMapping(value = "/link_sub")
    public String link(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "errore !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("subs", this.subService.findAll());
		
		return "users/reg_sub";	
	
    }
	
	@GetMapping(value="/{sub_id}/unlinksub/")
	public String unlinksub(@PathVariable("sub_id") Long subId)
	{
		User user=getCurrentUser();
		Sub sub=this.subService.findById(subId);
		user.setSub(null);
		user.setSubexp(null);
		this.userService.update(user);
		
		return "redirect:/";
	}
	
	@PostMapping(value="/addsub")
	public String registrasub(@RequestParam(value="sub") String subId)
	{
		
		User user=getCurrentUser();
		Sub sub=this.subService.findById(Long.parseLong(subId));
		user.setSub(sub);
		LocalDate date=LocalDate.now().plusDays(30);
		user.setSubexp(date);
		this.userService.update(user);
		
		return "redirect:/";
	}
	
	
	
/////////////////////////ADDRESS/////////////////////////////////////
	//Funziona
	@GetMapping(value = "/addresslist")
	public String listAddress( Model uiModel) {
		
		User user =getCurrentUser();
		List<Address> allAddresses = this.addressService.findByUserId(user.getUser_id());
		
		uiModel.addAttribute("address", allAddresses); //quello che restituisco alla vista
		return "users/addresslist";
	}

	@PostMapping(value = "/editaddress")
	public String editAddress(@RequestParam("address_id") Long address_id, Model uiModel) {
		
		Address a = this.addressService.findById(address_id);
		uiModel.addAttribute("address", a);
		return "users/addressupdate";
	}
	
	@PostMapping(value ="/addressup")
	public String addressUpdate(@ModelAttribute("newAddress") Address newAddress, BindingResult br, Model uiModel) {
		newAddress.setUser(getCurrentUser());
		this.addressService.update(newAddress);
		return "redirect:/users/addresslist";
	}
	
	//rimanda solo alla addressform per aggiungere un nuovo address
	@GetMapping(value="/addressadd")
	public String addAddress(Model uiModel) {
		uiModel.addAttribute("address", new Address());
		
		return "users/addressform";
	}
	
	//mi d� errore con GET premendo sulla X della vista
	/*@PostMapping(value = "/deleteaddress")
	public String deleteAddress(@RequestParam("address_id") Long address_id) {
	
		this.addressService.delete(address_id);
		
		return "redirect:/users/addressform";
	}*/
	
	@PostMapping(value = "/addresssave")
	public String saveAddress(@ModelAttribute("newAddress") Address newAddress, BindingResult br, Model uiModel) {
		User u = getCurrentUser();
		List <Address> allAddresses = this.addressService.findByUserId(u.getUser_id());
		if (allAddresses.size()>=3)
		{
			String errorMessage = "Hai gi� 3 indirizzi! Non puoi aggiungerne altri!";
			uiModel.addAttribute("address", new Address());
			uiModel.addAttribute("errorMessage", errorMessage);
			return "users/addressform";
		}else {
			this.addressService.create(newAddress.getCap(), newAddress.getCity(), newAddress.getStreet(), newAddress.getCiv_num(), u);
			//this.addressService.update(newAddress);
			return "redirect:/users/addresslist";
		}
		
	}		

////////////////////////////PURCHASE////////////////////////////////////////
	@GetMapping(value = "/purchase")
	public String purchase(Model uiModel) {
		User logged_user = getCurrentUser();
		List <Address> addresses = addressService.findByUserId(logged_user.getUser_id());
		uiModel.addAttribute("addresses", addresses);
		uiModel.addAttribute("total", logged_user.getCarts().getTotal());
		if(logged_user.getSub()!= null) {
			double total = logged_user.getCarts().getTotal();
			double discount = logged_user.getSub().getDiscount();
			double newtotal = total - (total*discount/100);
			uiModel.addAttribute("newtotal", newtotal);
			uiModel.addAttribute("discount", logged_user.getSub().getDiscount());
		}else {
			uiModel.addAttribute("newtotal", logged_user.getCarts().getTotal());
			uiModel.addAttribute("discount", 0);
		}
		return "users/purchase";
	}
	
	@RequestMapping(value="/savepurchase", method = RequestMethod.POST)
	public String savepurchase(@RequestParam ("paymeth")String paymeth, @RequestParam ("addr") Long addressId, @RequestParam ("total") double total) {
		Long cartId = getCurrentUser().getCarts().getCart_id();
		List <Cart_item> items = this.cartItemService.findByCart(cartId);
		Purchase purchase = this.purchaseService.create(paymeth, java.time.LocalDate.now(), total, getCurrentUser(), this.addressService.findById(addressId));
		for(Cart_item i : items) {
			i.setPurchase(purchase);
			i.setCart(null);
			this.cartItemService.update(i);
		}
		return "redirect:/";
	}
	
	@GetMapping(value ="/myorders")
	public String myorders(Model uiModel) {
		if(purchaseService.findByUserId(getCurrentUser().getUser_id()).size() > 0) {
			List <Purchase> purchases = purchaseService.findByUserId(getCurrentUser().getUser_id());
			List<List<Cart_item>> cartitems = new ArrayList<List<Cart_item>>();
			List<List<Item>> items = new ArrayList<List<Item>>();
			List<Address> addresses = new ArrayList<Address>();
			for (Purchase p : purchases) {
				cartitems.add(cartItemService.findByPurchaseCode(p.getPurchase_id()));
				addresses.add(addressService.findById(p.getAddress().getAddress_id()));
			}
			for(List<Cart_item> c : cartitems) {
				List<Item> it = new ArrayList<Item>();
				for (Cart_item i : c) {
					int quantita = i.getQuantity();
					for (int z=0; z<quantita; z++) {
						it.add(itemService.findById(i.getItem().getItem_id()));
					}
				}
				items.add(it);
			}
			uiModel.addAttribute("items", items);
		}else {
			String message = "Non hai ancora effettuato nessun acquisto.";
			uiModel.addAttribute("message", message);
		}
		return "users/myorders";
	}

///////////////////////////AUTOWIRED//////////////////////////////////////////
	
	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@Autowired
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setSubService(SubService subService) {
		this.subService = subService;
	}
	
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
}