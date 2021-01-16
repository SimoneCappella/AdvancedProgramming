package it.univpm.hhc.controller;


import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.qos.logback.classic.Logger;
import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.services.CartService;

@RequestMapping("/cart")
public class CartController {

	private CartService cartService;
	
	@GetMapping(value = "/list")
	public String list (Model uiModel) {
		List<Cart> cart = this.cartService.findAll();
		uiModel.addAttribute("cart", cart );
		return "cart/list";
	}
	
}
