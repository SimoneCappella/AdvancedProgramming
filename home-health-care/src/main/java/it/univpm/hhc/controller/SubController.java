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

import it.univpm.hhc.model.entities.Sub;
import it.univpm.hhc.services.SubService;

@RequestMapping("/subs")
@Controller
public class SubController {
	
	private final Logger logger = LoggerFactory.getLogger(SubController.class);
	
	private SubService subService;
	
	@GetMapping(value = "/list")
	public String list(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		logger.info("Listing subs");
		List<Sub> allSubs = this.subService.findAll();
		
		uiModel.addAttribute("subs", allSubs);  //quello che restituisco alla vista
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "subs/list";
	}
	
	@GetMapping(value = "/{subId}/delete")//occhio devo gestire la rimozione a cascata
	public String delete(@PathVariable("subId") String subId) {
		this.subService.delete(new Long(subId));
		
		return "redirect:/subs/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("sub", new Sub());
		
		return "subs/form";
	}

	@GetMapping(value="/{subId}/edit")//occhio devo gestire la modifica a cascata
	public String edit(@PathVariable("subId") String subId, 
			Model uiModel) {
		
		Sub s = this.subService.findById(new Long(subId));
		uiModel.addAttribute("sub", s);
		
		return "subs/form";
	}

	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newSub") Sub newSub, BindingResult br) {
		this.subService.update(newSub);
		
		return "redirect:/subs/list/";
		
		// return "redirect:singers/list"; // NB questo non funzionerebbe!
		
	}
	
	@Autowired
	public void setSubService(SubService subService) {
		this.subService = subService;
	}
}
