package it.univpm.hhc.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.mockito.internal.matchers.CompareTo;
import org.mockito.internal.matchers.Null;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import it.univpm.hhc.model.entities.Cart;
import it.univpm.hhc.model.entities.Cart_item;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.services.CartItemService;
import it.univpm.hhc.services.CartService;
import it.univpm.hhc.services.ItemService;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserService;

@Controller
public class PublicController {

	private CartService cartService;
	private CartItemService cartItemService;
	private UserService UserService;
	private ItemService ItemService;
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
		User curUser=getCurrentUser();
		
		if(curUser!=null)
		{
			Sub sub= curUser.getSub();
			Long cart_id = (cartService.findByUserId(curUser.getUser_id())).getCart_id();
			List<Cart_item> items = cartItemService.findByCart(cart_id);
			model.addAttribute("userid", curUser.getUser_id());
			if(curUser.getSub()!=null)
			{	
				LocalDate date2 =java.time.LocalDate.now();
				int val= curUser.getSubexp().compareTo(date2);
				
				if(val<=0)
					{
						curUser.setSub(null);
						curUser.setSubexp(null);
						userService.update(curUser);
						model.addAttribute("messaggio"," Abbonamento Scaduto!");
					}
				else 
				{
					model.addAttribute("nomeSub",sub.getName());
					model.addAttribute("scadenza",curUser.getSubexp());
				}
			}
			item_number = items.size();
			for (Cart_item i : items){
				if(i.getQuantity() >= 1) {
					item_number += i.getQuantity()-1;
					total += i.getQuantity() * (i.getItem().getPrice());
				}
			}
			curUser.getCarts().setTotal(total);
			this.cartService.update(curUser.getCarts());
		}
		
		model.addAttribute("total", total);
		model.addAttribute("item_number", item_number);
		return "home";
	}
	
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
	
/////////////////////////ITEM/////////////////////////////////////
@GetMapping(value = "/itemlist")
public String itemlist(Model uiModel) {
List<Item> allItems = ItemService.findAll();

uiModel.addAttribute("items", allItems);

return "itemlist";
}
	
	@Autowired
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Autowired
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@Autowired
	public void setItemService(ItemService ItemService) {
		this.ItemService = ItemService;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	private UserService userService;
	
    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, 
//                            @RequestParam(value = "+", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "Username o Password errati !!";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("appName", appName);
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
           return "login";
//        }
//        return "redirect:/";
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
		

		this.cartService.create(0, 0, this.userService.create(newUser.getPassword(),newUser.getEmail(),newUser.getName(),newUser.getSurname()));
	
		return "redirect:/";
		
	}
	
    
    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
 
}
