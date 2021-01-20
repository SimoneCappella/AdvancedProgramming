package it.univpm.hhc.controller;

import java.util.List;

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
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;

@RequestMapping("/cart")
@Controller
public class CartController {
	
	//Dovremo inserire di mostrare le info del carrello dell'utente loggato e la possibilità di visualizzare tutti gli item nel carrello, 
	//di eliminarne uno o più e modificare le quantità
	
	private final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	private Long currentCart;

	private CartService cartService;
	private CartItemService cartItemService;
	
	//Ritorna correttamente gli  item nel carrello dell'utente "loggato", da implementare il get dell'id carrello associato all'utente in maniera dinamica
	@GetMapping(value = "/{cart_id}/list")
	public String list(@PathVariable("cart_id") Long cart_id, Model uiModel) {
		setCurrentCart(cart_id);
		logger.info("Listing carts");
		List<Cart_item> allItem = this.cartItemService.findByCart(cart_id);
		uiModel.addAttribute("items", allItem);  //quello che restituisco alla vista
		return "cart/list";
	}
	
	//Ok, funzionante
	@GetMapping(value = "/{cart_item_id}/delete")
	public String delete(@PathVariable("cart_item_id") Long cart_item_id) {
		Cart_item item = cartItemService.findByid(cart_item_id); //ottengo un'istanza del cart_item in questione
		this.cartItemService.delete(cart_item_id); 
		Cart cart = item.getCart();	//ottengo il carrello associato per poi ricavarmi l'id da passare alla vista
		Long cart_id = cart.getCart_id();
		return "redirect:/cart/"+cart_id+"/list";
	}
	

	@GetMapping(value="/{cart_item_id}/edit")//occhio devo gestire la modifica a cascata
	public String edit(@PathVariable("cart_item_id") long cart_item_id, Model uiModel) {
		
		Cart_item i = this.cartItemService.findByid(cart_item_id);
		uiModel.addAttribute("item", i);
		return "cart/form";
	}

	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newCartItem") Cart_item newCartItem,BindingResult br) {
		Cart c = cartService.findById(getCurrentCart());
		newCartItem.setCart(c);
		this.cartItemService.update(newCartItem);
		Long cart_id = getCurrentCart();
		return "redirect:/cart/"+cart_id+"/list";
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
}

