package it.univpm.hhc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.services.AddressService;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserService;
import it.univpm.hhc.services.AddressService;
import it.univpm.hhc.services.ItemService;

@RequestMapping("/admins")
@Controller
public class AdminController {

	private UserService userService;
	private AddressService addressService;
	private ItemService itemService;
	
	@Autowired
	ServletContext context;
	
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
	
	public String upimage(MultipartFile file) throws MaxUploadSizeExceededException{
		if( !(file.getContentType().toString()).equals("image/png") && !(file.getContentType().toString()).equals("image/jpg") && !(file.getContentType().toString()).equals("image/jpeg")) {
			
			return "0";
		}
		String p = context.getRealPath(".");
		StringBuilder fileNames = new StringBuilder();	  
			  Path fileNameAndPath = Paths.get(p, "WEB-INF", "media", file.getOriginalFilename());
			  fileNames.append(file.getOriginalFilename()+" ");
			  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  return file.getOriginalFilename();
	}
	
	@GetMapping(value = "/userlist")
	public String list(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		List<User> allUsers = this.userService.findAll();
		
		uiModel.addAttribute("users", allUsers);  //quello che restituisco alla vista
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "admins/userlist";
	}
	
	
	
	//Cambiare la form
	@GetMapping(value="/{userId}/useredit")//occhio devo gestire la modifica a cascata
	public String edit(@PathVariable("userId") String userId, 
			Model uiModel) {
		
		User u = this.userService.findById(new Long(userId));
		uiModel.addAttribute("user", u);
		
		return "admins/userform";
	}

	
	@PostMapping(value = "/usersave")
	public String saveUser(@ModelAttribute("newUser") User newUser, BindingResult br, Model uiModel) {
		String regexname ="^[A-Za-z]+$";
		String regexmail ="^[A-Za-z0-9+_.-]+@(.+)$";
		String regexpass = "^[A-Za-z0-9@#$%^&]*$";
    	Pattern patternmail = Pattern.compile(regexmail);
    	Pattern patternname = Pattern.compile(regexname);
    	Pattern patternpass = Pattern.compile(regexpass);
    	Matcher matchermail = patternmail.matcher(newUser.getEmail());
    	Matcher matchername = patternname.matcher(newUser.getName());
    	Matcher matchersurname = patternname.matcher(newUser.getSurname());
    	Matcher matcherpass = patternpass.matcher(newUser.getPassword());
    	User u = userService.findById(newUser.getUser_id());
    	List<String> err = new ArrayList<String>();
    	boolean flag = true;
    	if(!matchermail.matches()) {
    		err.add("Email non valida.");
    		flag = false;
    	}
    	if(!matchername.matches()) {
    		err.add("Nome non valido.");
    		flag = false;
    	}
    	if(!matchersurname.matches()) {
    		err.add("Cognome non valido.");
    		flag = false;
    	}
    	if(!matcherpass.matches()) {
    		err.add("Password non valida.");
    		flag = false;
    	}
    	if(flag == true && newUser.getPassword().length() == 0) {
    		
    		if(getCurrentUser().getUser_id() == newUser.getUser_id() && (getCurrentUser().isRole() != newUser.isRole() ||
    				getCurrentUser().getEmail() != newUser.getEmail())){
    			newUser.setPassword(u.getPassword());
        		this.userService.update(newUser);
    			return "redirect:/logout";
    		}
    		newUser.setPassword(u.getPassword());
    		this.userService.update(newUser);
    		String errorMessage = "Profilo modificato con successo.";
        	uiModel.addAttribute("user", newUser);
    		uiModel.addAttribute("errorMessage", errorMessage);
    		return "admins/userform";
    	}else if(flag == true && !(u.getPassword()).equals(newUser.getPassword())) {
    		if(getCurrentUser().getUser_id() == newUser.getUser_id() && (getCurrentUser().isRole() != newUser.isRole() ||
    				getCurrentUser().getEmail() != newUser.getEmail())){
    			this.userService.updatewithpass(newUser);
    			return "redirect:/logout";
    		}
    		String errorMessage = "Profilo modificato con successo.";
        	uiModel.addAttribute("user", newUser);
    		uiModel.addAttribute("errorMessage", errorMessage);
    		this.userService.updatewithpass(newUser);
    		return "admins/userform";
    	}
    	uiModel.addAttribute("user", newUser);
    	uiModel.addAttribute("errorMessage", err);
		return "admins/userform";
	}
	
	
	@GetMapping(value = "/{userId}/userdelete")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("userId") long userId, Model uiModel) {
		if(userId == getCurrentUser().getUser_id()) {
			this.userService.delete(userId);	
			return "redirect:/logout";
		}else {
			this.userService.delete(userId);	
			return "redirect:/admins/userlist";
		}
	}
	
	@GetMapping(value = "/{userId}/userdisable")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("userId") long userId) {
		if(userId == getCurrentUser().getUser_id()) {
			User user = getCurrentUser();
			user.setActive(false);
			userService.update(user);
			return "redirect:/logout";
		}
		User user = this.userService.findById(userId);
		user.setActive(!user.isActive());
		userService.update(user);
		return "redirect:/admins/userlist";
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private SubService subService;
	
	@GetMapping(value = "/sublist")
	public String sublist(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		List<Sub> allSubs = this.subService.findAll();
		
		uiModel.addAttribute("subs", allSubs);  //quello che restituisco alla vista
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "admins/sublist";
	}
	@GetMapping(value = "/{subId}/subdelete")//occhio devo gestire la rimozione a cascata
	public String subdelete(@PathVariable("subId") String subId) {
		this.subService.delete(new Long(subId));
		
		return "redirect:/admins/sublist";
	}
	
	@GetMapping(value = "/subadd")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("sub", new Sub());
		
		return "admins/subform";
	}

	@GetMapping(value="/{subId}/subedit")//occhio devo gestire la modifica a cascata
	public String subedit(@PathVariable("subId") String subId, 
			Model uiModel) {
		
		Sub s = this.subService.findById(new Long(subId));
		uiModel.addAttribute("sub", s);
		
		return "admins/subform";
	}

	
	@PostMapping(value = "/subsave")
	public String save(@ModelAttribute("newSub") Sub newSub, BindingResult br, Model uiModel) {
		String regexname = "^[A-Za-z0-9.,:;!?()\\s]+$";
		String regexprice = "\\d+(.\\d{1,2})?$";
		String regexdiscount = "^[0-9]+$";
		Pattern patternname = Pattern.compile(regexname);
		Pattern patterndiscount = Pattern.compile(regexdiscount);
		Pattern patternprice = Pattern.compile(regexprice);
		Matcher matchername = patternname.matcher(newSub.getName());
		Matcher matcherdiscount = patterndiscount.matcher(String.valueOf(newSub.getDiscount()));
		Matcher matcherprice = patternprice.matcher(String.valueOf(newSub.getPrice()));
		List<String> err = new ArrayList<String>();
		boolean flag = true;
		if(!matchername.matches()) {
			err.add("Hai inserito caratteri proibiti nel nome dell'abbonamento.");
			flag = false;
		}
		if(!matcherdiscount.matches() || newSub.getDiscount() == 0.0 || newSub.getDiscount() < 1 || newSub.getDiscount() > 100) {
			err.add("Puoi inserire solo un numero intero compreso tra 1 e 100.");
			flag = false;
		}
		if(!matcherprice.matches() || newSub.getPrice() == 0.0) {
			err.add("Puoi inserire solo numeri e punti e massimo due cifre decimali.");
			flag = false;
		}
		List<Sub> subs = new ArrayList<Sub>();
		subs = subService.findByName(newSub.getName());
		if(flag == true && subs.size() > 0) {
			for(Sub s: subs) {
				s.setName(newSub.getName());
				s.setDiscount(newSub.getDiscount());
				s.setPrice(newSub.getPrice());
				subService.update(s);
			}
			String message = "L'abbonamento '" + newSub.getName() + "' è stato modificato con successo.";
			uiModel.addAttribute("errorMessage", message);
			uiModel.addAttribute("sub", newSub);
			return "admins/subform";
		} else if(flag == true && subs.size() == 0) {
			this.subService.create(newSub.getName(), newSub.getPrice(), newSub.getDiscount());
			String message = "L'abbonamento '" + newSub.getName() + "' è stato aggiunto con successo.";
			uiModel.addAttribute("errorMessage", message);
			uiModel.addAttribute("sub", newSub);
			return "admins/subform";
		}
		uiModel.addAttribute("sub", newSub);
		uiModel.addAttribute("errorMessage", err);
		return "admins/subform";

		
		
		// return "redirect:singers/list"; // NB questo non funzionerebbe!
		
	}
	
	@Autowired
	public void setSubService(SubService subService) {
		this.subService = subService;
	}
//////////////////////////ADDRESS////////////////////////////////
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

	
	@PostMapping(value = "/save")
	public String saveAddress(@ModelAttribute("newAddress") Address newAddress, BindingResult br) {
		
		this.addressService.update(newAddress);
		
		return "redirect:/addresses/list/";
		
	}
	
	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
//////////////////////////ITEM////////////////////////////////
/*@GetMapping(value = "/itemlist")
public String itemlist(Model uiModel) {
List<Item> allItems = ItemService.findAll();

uiModel.addAttribute("items", allItems);

return "users/itemlist";
}*/


@GetMapping(value="/itemadd")
public String addItem(Model uiModel) {
Item item = new Item();
uiModel.addAttribute("item", item);

return "admins/itemform";
}

@GetMapping(value = "/{item_id}/deleteitem")
public String deleteItem(@PathVariable("item_id") Long itemId) {
//Address address= addressService.findById(address);
this.itemService.delete(itemId);
return "redirect:/itemlist";
}

@GetMapping(value="/{item_id}/itemedit")//occhio devo gestire la modifica a cascata
public String editItem(@PathVariable("item_id") String item_id, Model uiModel) {

Item i = this.itemService.findById(new Long(item_id));
uiModel.addAttribute("item", i);

return "admins/itemform";
}

@PostMapping(value = "/itemsave")
public String saveItem(@RequestParam("item_id") String item_id, @RequestParam("image") MultipartFile file, @RequestParam("title") String title, //bisogna passare l'id e metterlo in hidden nel form
		@RequestParam("description") String description , @RequestParam("price") String price, Model uiModel) {
	Item newItem = new Item();
	newItem.setTitle(title);
	newItem.setDescription(description);
	//newItem.setPrice(price);
	String regexname = "^[0-9A-Za-z\\s]+$";
	String regexdescr = "^[A-Za-z0-9.,:;!?()\\s]+$";
	String regexprice = "\\d+(.\\d{1,2})?$";
	Pattern patternname = Pattern.compile(regexname);
	Pattern patterndescr = Pattern.compile(regexdescr);
	Pattern patternprice = Pattern.compile(regexprice);
	Matcher matchername = patternname.matcher(newItem.getTitle());
	Matcher matcherdescr = patterndescr.matcher(newItem.getDescription());
	Matcher matcherprice = patternprice.matcher(price);
	List <String> err = new ArrayList<String>();
	boolean flag = true;
	if(!matchername.matches()) {
		err.add("Hai inserito caratteri proibiti nel nome.");
		flag = false;
	}
	if(!matcherdescr.matches()) {
		err.add("Hai inserito caratteri proibiti nella descrizione.");
		flag = false;
	}
	if(!matcherprice.matches()) {
		err.add("Puoi inserire solo numeri e punti nel prezzo e massimo due cifre decimali.");
		uiModel.addAttribute("item", newItem);
		uiModel.addAttribute("errorMessage", err);
		return "admins/itemform";
	}
	newItem.setPrice(Double.valueOf(price));
	if(newItem.getPrice() <= 0) {
		err.add("Devi inserire un prezzo maggiore di 0.");
		flag = false;
	}
	String path;
	List<Item> item = new ArrayList<Item>();
	if (!item_id.equals("")) {
		item = itemService.findByIdList(Long.parseLong(item_id));
	}
	if (flag == true && item.size() > 0) {
		for (Item i : item) {
			path = upimage(file);
			if (path.equals("0")) {
				newItem.setItem_id(Long.parseLong(item_id));
				uiModel.addAttribute("errorMessage", "Il formato dell'immagine non è supportato o non è stata selezionata alcun immagine!");
				uiModel.addAttribute("item", newItem);
				return "admins/itemform";
			}
			String s="media/"+path;
			i.setTitle(newItem.getTitle());
			i.setDescription(newItem.getDescription());
			i.setPrice(newItem.getPrice());
			i.setImage(s);
			this.itemService.update(i);	
		}	
		String message = "L'oggetto '" + newItem.getTitle() + "' è stato modificato con successo.";
		newItem.setItem_id(Long.parseLong(item_id));
		uiModel.addAttribute("errorMessage", message);
		uiModel.addAttribute("item", newItem);
		return "admins/itemform";
	} else if(flag == true && item.size() == 0) {
		path = upimage(file);
		if (path.equals("0")) {
			uiModel.addAttribute("errorMessage", "Il formato dell'immagine non è supportato o non è stata selezionata alcun immagine!");
			uiModel.addAttribute("item", newItem);
			return "admins/itemform";
		}
		List <Item> i = itemService.findByTitle(title);
		if(i.size() > 0) {
			uiModel.addAttribute("errorMessage", "Esiste già un articolo con questo nome!");
			uiModel.addAttribute("item", newItem);
			return "admins/itemform";
		}
		this.itemService.create(newItem.getTitle(), newItem.getDescription(), newItem.getPrice(), "media/"+path);
		String message = "L'oggetto '" + newItem.getTitle() + "' è stato aggiunto con successo.";
		uiModel.addAttribute("errorMessage", message);
		uiModel.addAttribute("item", newItem);
		return "admins/itemform";
	}
	if(!item_id.equals("")) {
	newItem.setItem_id(Long.parseLong(item_id));
	}
	uiModel.addAttribute("item", newItem);
	uiModel.addAttribute("errorMessage", err);
	return "admins/itemform";
}


@Autowired
public void setItemService(ItemService ItemService) {
this.itemService = ItemService;
}
	
}
