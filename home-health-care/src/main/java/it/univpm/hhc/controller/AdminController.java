package it.univpm.hhc.controller;

import java.util.List;

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

import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.model.entities.Address;
import it.univpm.hhc.services.AddressService;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserService;
import it.univpm.hhc.services.AddressService;

@RequestMapping("/admins")
@Controller
public class AdminController {

	private UserService userService;
	private AddressService addressService;
	
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
	public String saveUser(@ModelAttribute("newUser") User newUser, BindingResult br) {
		
		this.userService.update(newUser);
		
		return "redirect:/admins/userlist/";
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
		user.setActive(false);
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
	public String save(@ModelAttribute("newSub") Sub newSub, BindingResult br) {
		this.subService.update(newSub);
		
		return "redirect:/admins/sublist/";
		
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
}
