package it.univpm.hhc.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.text.IsEmptyString;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.mockito.internal.matchers.CompareTo;
import org.mockito.internal.matchers.Null;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jayway.jsonpath.internal.function.text.Concatenate;

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

	public static String uploadDirectory = System.getProperty("user.dir");
	private CartService cartService;
	private CartItemService cartItemService;
	private UserService UserService;
	private ItemService ItemService;
	@Autowired
	String appName;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) { // DA METTERE NELLO USER CONTROLLER
		System.out.println("Home Page Requested,  locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("appName", appName);
		int item_number = 0;
		double total = 0;
		User curUser = getCurrentUser();

		if (curUser != null) {
			Sub sub = curUser.getSub();
			Cart cart = (cartService.findByUserId(curUser));
			List<Cart_item> items = cartItemService.findByCart(cart);
			model.addAttribute("userid", curUser.getUser_id());
			if (curUser.getSub() != null) {
				LocalDate date2 = java.time.LocalDate.now();
				int val = curUser.getSubexp().compareTo(date2);

				if (val <= 0) {
					curUser.setSub(null);
					curUser.setSubexp(null);
					userService.update(curUser);
					model.addAttribute("messaggio", " Abbonamento Scaduto!");
				} else {
					model.addAttribute("nomeSub", sub.getName());
					model.addAttribute("scadenza", curUser.getSubexp());
				}
			}
			item_number = items.size();
			for (Cart_item i : items) {
				if (i.getQuantity() >= 1) {
					item_number += i.getQuantity() - 1;
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

	public User getCurrentUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User logged_user = userService.findByEmail(username);
			return logged_user;

		} else {
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
		if (error != null) {
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
		if (error != null) {
			errorMessage = "Username o Password errati !!";
		}
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("user", new User());

		return "registration";
	}
	
	@GetMapping(value = "/chisiamo")
	public String chisiamoPage() {
		
		return "chisiamo";
	}
	
	@GetMapping(value = "/lavoraconnoi")
	public String lavoraPage() {
		
		return "lavoraconnoi";
	}
	
	@GetMapping(value = "/obiettivoprogetto")
	public String obiettivoprogettoPage() {
		
		return "obiettivoprogetto";
	}

	@PostMapping(value = "/save")
	public String savenew(@ModelAttribute("newUser") User newUser, BindingResult br, Model model) {

		String regexmail = "^[A-Za-z0-9+_.-]+@(.+)$";
		String regexpass = "^[A-Za-z0-9@#$%^&]+$";
		String regexname = "^[A-Za-z]+$";
		Pattern patternmail = Pattern.compile(regexmail);
		Pattern patternpass = Pattern.compile(regexpass);
		Pattern patternname = Pattern.compile(regexname);
		List<User> user = null;
		user = userService.findByEmail2(newUser.getEmail());
		List<String> err = new ArrayList<String>();
		Matcher matchermail = patternmail.matcher(newUser.getEmail());
		Matcher matcherpass = patternpass.matcher(newUser.getPassword());
		Matcher matchername = patternname.matcher(newUser.getName());
		Matcher matchersurname = patternname.matcher(newUser.getSurname());
		boolean flag = true;
		String mes;
		if (newUser.getName().isEmpty() || newUser.getName().length() < 3 || newUser.getName().length() > 20
				|| !matchername.matches()) {
			err.add("Nome non valido.");
			flag = false;
		}

		if (newUser.getSurname().isEmpty() || newUser.getSurname().length() < 3 || newUser.getSurname().length() > 20
				|| !matchersurname.matches()) {
			err.add("Cognome non valido.");
			flag = false;
		}

		if (newUser.getEmail().isEmpty() || newUser.getEmail().length() < 4 || newUser.getEmail().length() > 20
				|| !matchermail.matches()) {
			err.add("Email non valida.");
			flag = false;
		}

		if (user.size() > 0) {
			err.add("Utente gi� registrato");
			flag = false;
		}
		if (newUser.getPassword().isEmpty() || newUser.getPassword().length() < 7 || newUser.getPassword().length() > 20
				|| !matcherpass.matches()) {
			err.add("Password non valida.");
			flag = false;
		}

		if (flag == true) {
			this.cartService.create(0, 0, this.userService.create(newUser.getPassword(), newUser.getEmail(),
					newUser.getName(), newUser.getSurname()));
			return "redirect:/";
		}
		model.addAttribute("errorMessage", err);
		return "registration";

	}
	
	@GetMapping(value = "/up")
	public String up() {
		return "upload";
	}
	
	@RequestMapping(value ="/upload", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file, Model uiModel) {
		StringBuilder fileNames = new StringBuilder();	  
			  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  return "redirect:/";
	}


	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
