package it.univpm.hhc.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;
import it.univpm.hhc.services.UserService;

@Controller
public class PublicController {

	private CartService cartService;
	private CartItemService cartItemService;
	private UserService UserService;
	private static User logged_user;

	@Autowired
	String appName;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {       //DA METTERE NELLO USER CONTROLLER
		System.out.println("Home Page Requested,  locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);		
		model.addAttribute("appName", appName);
		
		int item_number = 0;
		double total = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			logged_user = userService.findByEmail(username);
			Long cart_id = (cartService.findByUserId(logged_user.getUser_id())).getCart_id();
			List<Cart_item> items = cartItemService.findByCart(cart_id);
			item_number = items.size();
			for (Cart_item i : items){
				if(i.getQuantity() > 1) {
					item_number += i.getQuantity()-1;
					total += i.getQuantity() * (i.getItem().getItemPrice());
				}
			}
		}else {
			String username = principal.toString();
		}
		
		model.addAttribute("total", total);
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
	
	/////////////////////////////////////////////////////////////////////////////////////////
	private UserService userService;
	
    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
//                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "Username o Password errati !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("appName", appName);
        return "login";
    }
    
    @GetMapping(value = "/register")
    public String registerPage(@RequestParam(value = "error", required = false) String error, Model model) {
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "Username o Password errati !!";
        }
        model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("user", new User());
		
        return "registration";
    }
    @PostMapping(value = "/save")
	public String savenew(@ModelAttribute("newUser") User newUser, BindingResult br) {
		
		
		this.userService.create(newUser.getPassword(),newUser.getEmail(),newUser.getName(),newUser.getSurname());
		
		return "redirect:/";
		
	}
	
    
    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    public static User getLogged_user() {
		return logged_user;
	}

	public static void setLogged_user(User logged_user) {
		PublicController.logged_user = logged_user;
	}
}
