package it.univpm.hhc.controller;

import java.util.List;

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

import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.services.SubService;
import it.univpm.hhc.services.UserDetailsService;

@RequestMapping("/admins")
@Controller
public class AdminController {

	private UserDetailsService userService;

	
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
	public String delete(@PathVariable("userId") String userId) {
		this.userService.delete(new Long(userId));
		
		return "redirect:/admins/userlist";
	}
	
	@Autowired
	public void setUserService(UserDetailsService userService) {
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
	
}
