/*package it.univpm.hhc.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;

@Controller
public class HomeController {
	
	private CartService cartService;
	private CartItemService cartItemService;
	
	@Autowired
	String appName;

	     
	@RequestMapping(value = "/asdasdads", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested,  locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);		
		model.addAttribute("appName", appName);

		Long cart_id = (long) 1; //potrò ricavare il codice del carrello dell'utente loggato dopo la funzione login
		List<Cart_item> items = cartItemService.findByCart(cart_id);
		int item_number = items.size();
		for (Cart_item i : items){
			if(i.getQuantity() > 1) {
				item_number += i.getQuantity()-1;
			}
		}
		model.addAttribute("item_number", item_number);
		return "home";
	}
	
	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

}*/
