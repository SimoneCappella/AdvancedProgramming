package it.univpm.hhc.controller;

import java.util.List;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserDetailsService;
import net.bytebuddy.asm.Advice.This;

@RequestMapping("/users")
@Controller
public class UserController {
	
	private PublicController PC;
	private UserDetailsService userService;
	private SubService subService;
	

//	dovrebbe non servire	
//	@GetMapping(value = "/add") //prima era add
//	public String add(Model uiModel) {
//		
//		uiModel.addAttribute("user", new User());
//		
//		return "users/form";
//	}
	
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
	
	@Autowired
	public void setUserService(UserDetailsService userService) {
		this.userService = userService;
	}
	
	//CART//////////////////////////////////////////////////////////////////////////////////////
	
	private Long currentCart;

	private CartService cartService;
	private CartItemService cartItemService;
	
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
	
	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	//SUB//////////////////////////////////////////////////////////////////////////////////////////////
	private Long currentUser;
	
	@GetMapping("/link/sub")
	public String link(Model uiModel)
	{
		uiModel.addAttribute("subs", this.subService.findAll());
		uiModel.addAttribute("users", this.subService.findAll());
		return "users/addsub";	
	}
	
	
	@GetMapping("/link")
	public String link(
			@RequestParam(value = "next",required = false)String next,
			@RequestParam(value="user")Long userId,
			@RequestParam(value="sub")Long subId)
	{
		User user=this.userService.findById(userId);
		Sub sub=this.subService.findById(subId);
		
		user.getSub().addUser(user);
		sub.getUsers().add(user);
		this.userService.update(user);
		
		if(next ==null || next.length()==0) {
			next="/";// da modificare o
		}
		return "redirect:" + next;
	}
	
	
//	User corrente	
//	public Long getCurrentUser() {
//		return currentUser;
//	}
//	
//	public void SetCurrentUser(Long currentUser) {
//		this.currentUser=currentUser;
//		
//	}
	
	@Autowired
	public void setSubService(SubService subService) {
		this.subService = subService;
	}
	
}