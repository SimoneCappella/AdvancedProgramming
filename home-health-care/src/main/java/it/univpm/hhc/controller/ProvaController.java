package it.univpm.hhc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univpm.hhc.model.entities.Prova;
import it.univpm.hhc.services.ProvaService;

@RequestMapping("/prova")
@Controller
public class ProvaController {
	
	private ProvaService provaService;
	
	@GetMapping(value="/list")
	public String list(Model uiModel) {
		
		List<Prova> prove = this.provaService.findAll();
		uiModel.addAttribute("prove", prove);
		
		return "prove/list";
	}
	
	@GetMapping(value="/add")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("album", new Prova());
		
		return "prove/form";
	}
	
	@PostMapping(value="/save")
	public String save(@ModelAttribute("album") Prova prova, BindingResult bindingResult) {
		
		this.provaService.update(prova);
		
		return "redirect:/prove/list";
	}
	
	
	@Autowired
	public void setProvaService(ProvaService provaService) {
		this.provaService = provaService;
	}
}
