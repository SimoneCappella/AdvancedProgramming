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
import it.univpm.hhc.services.CartService;

@RequestMapping("/carts")
@Controller
public class CartController {
	
	private final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	private CartService cartService;
	
	@GetMapping(value = "/list")//adesso fa vedere tutti i carrelli, ci servira poi un solo carrello da ritornare
	public String list(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		logger.info("Listing carts");
		List<Cart> allCarts = this.cartService.findAll();
		
		uiModel.addAttribute("carts", allCarts);  //quello che restituisco alla vista
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "carts/list";
	}
	
	@GetMapping(value = "/{cartId}/delete")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("cartId") String cartId) {
		this.cartService.delete(new Long(cartId));
		
		return "redirect:/carts/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("cart", new Cart());
		
		return "carts/form";
	}

	@GetMapping(value="/{cartId}/edit")//occhio devo gestire la modifica a cascata
	public String edit(@PathVariable("cartId") String cartId, 
			Model uiModel) {
		
		Cart c = this.cartService.findById(new Long(cartId));
		uiModel.addAttribute("cart", c);
		
		return "carts/form";
	}

	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newCart") Cart newCart, BindingResult br) {
		this.cartService.update(newCart);
		
		return "redirect:/carts/list/";
		
		// return "redirect:singers/list"; // NB questo non funzionerebbe!
		
	}
	
	@Autowired
	public void setProvaService(CartService cartService) {
		this.cartService = cartService;
	}
}

