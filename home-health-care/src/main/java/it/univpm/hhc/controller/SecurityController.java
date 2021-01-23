/*package it.univpm.hhc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.hhc.model.entities.User;
import it.univpm.hhc.services.UserService;

@Controller // Controller usato per il login
public class SecurityController 
{
	private UserService userService;
	
	@Autowired
	String appName; // = "SingersApp";
	
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
}*/

