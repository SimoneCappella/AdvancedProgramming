package it.univpm.hhc.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import it.univpm.hhc.utils.LocalDateToDateConverter;

import org.hibernate.mapping.Set;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;
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
	private Long currentCart;

	private CartService cartService;
	private CartItemService cartItemService;

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
	@GetMapping(value="/{userId}/edit")//occhio devo gestire la modifica a cascata
	public String edit(@PathVariable("userId") String userId, 
			Model uiModel) {
		
		User u = this.userService.findById(new Long(userId));
		uiModel.addAttribute("user", u);
		
		return "users/form";
	}
	
	@GetMapping(value = "/{userId}/delete")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("userId") String userId) {
		this.userService.delete(new Long(userId));//anziché eliminare dovrà disabilitare (DA CAMBIARE)
		//QUI DA RICHIAMARE IL LOGOUT
		return "redirect:/";
	}

	
	@PostMapping(value = "/save")
	public String saveUser(@ModelAttribute("newUser") User newUser, BindingResult br) {
		
		this.userService.update(newUser);
		
		return "redirect:/users/list/";
		
	}
	
//CART//////////////////////////////////////////////////////////////////////////////////////
	
	//Ritorna correttamente gli  item nel carrello dell'utente "loggato", da implementare il get dell'id carrello associato all'utente in maniera dinamica
	@GetMapping(value = "/{cart_id}/cartlist")
	public String list(@PathVariable("cart_id") Long cart_id, Model uiModel) {
		setCurrentCart(cart_id);
		List<Cart_item> allItem = this.cartItemService.findByCart(cart_id);
		uiModel.addAttribute("items", allItem);  //quello che restituisco alla vista
		return "users/cartlist";
	}
	
	//Ok, funzionante
	@GetMapping(value = "/{cart_item_id}/cartdelete")
	public String delete(@PathVariable("cart_item_id") Long cart_item_id) {
		Cart_item item = cartItemService.findByid(cart_item_id); //ottengo un'istanza del cart_item in questione
		this.cartItemService.delete(cart_item_id); 
		Cart cart = item.getCart();	//ottengo il carrello associato per poi ricavarmi l'id da passare alla vista
		Long cart_id = cart.getCart_id();
		return "redirect:/users/"+cart_id+"/cartlist";
	}
	

	@GetMapping(value="/{cart_item_id}/cartedit")
	public String edit(@PathVariable("cart_item_id") long cart_item_id, Model uiModel) {
		
		Cart_item i = this.cartItemService.findByid(cart_item_id);
		uiModel.addAttribute("item", i);
		return "users/cartform";
	}

	
	@PostMapping(value = "/cartsave")
	public String save(@ModelAttribute("newCartItem") Cart_item newCartItem,BindingResult br) {
		Cart c = cartService.findById(getCurrentCart());
		newCartItem.setCart(c);
		this.cartItemService.update(newCartItem);
		Long cart_id = getCurrentCart();
		return "redirect:/users/"+cart_id+"/cartlist";
	}
	
	public Long getCurrentCart() {
		return currentCart;
	}

	public void setCurrentCart(Long currentCart) {
		this.currentCart = currentCart;
	}
	
	//SUB//////////////////////////////////////////////////////////////////////////////////////////////

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
		
//	@GetMapping(value="/{sub_id}/addsub")
//	public String addsub(@PathVariable("sub") Long subId)		
//	{
//		User user=getCurrentUser();
//		Sub sub=this.subService.findById(subId);
//		
//		user.getSub().addUser(user);
//		sub.getUsers().add(user);
//		this.userService.update(user);
//		
//		if(next ==null || next.length()==0) {
//			next="/";// da modificare o
//		}
//		return "redirect:" + next;
//	}
//		
	
	
	@GetMapping(value="/{sub_id}/addsub")
	public String registrasub(@PathVariable("sub_id") Long subId)
	{
		User user=getCurrentUser();
		Sub sub=this.subService.findById(subId);
		user.setSub(sub);
		LocalDate date=LocalDate.now().plusDays(30);
		user.setSubexp(date);
		this.userService.update(user);
		
		return "redirect:/";
	}
	
	
	
/////////////////////////ADDRESS/////////////////////////////////////
	@GetMapping(value="/{addressId}/edit")//occhio devo gestire la modifica a cascata
	public String editAddress(@PathVariable("addressId") String addressId, 
			Model uiModel) {
		
		Address a = this.addressService.findById(new Long(addressId));
		uiModel.addAttribute("address", a);
		
		return "addresses/form";
	}
	
	
	@GetMapping(value="/add")
	public String addAddress(Model uiModel) {
		
		uiModel.addAttribute("address", new Address());
		
		return "addresses/form";
	}
	
	
	@GetMapping(value = "/{addressId}/delete")//occhio devo gestire la rimozione a cascata
	public String deleteAddress(@PathVariable("addressId") String addressId) {
		this.addressService.delete(new Long(addressId));//anziché eliminare dovrà disabilitare (DA CAMBIARE)
		//QUI DA RICHIAMARE IL LOGOUT
		return "redirect:/";
	}

///////////////////////////AUTOWIRED//////////////////////////////////////////
	
	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
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