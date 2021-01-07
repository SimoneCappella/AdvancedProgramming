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

import it.univpm.hhc.model.entities.Prova;
import it.univpm.hhc.services.ProvaService;

@RequestMapping("/prove")
@Controller
public class ProvaController {
	
	private final Logger logger = LoggerFactory.getLogger(ProvaController.class);
	
	private ProvaService provaService;
	
	@GetMapping(value = "/list")
	public String list(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		logger.info("Listing prove");
		List<Prova> allProve = this.provaService.findAll();
		
		uiModel.addAttribute("prove", allProve);
		uiModel.addAttribute("numProve", allProve.size());
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "prove/list";
	}
	
	@GetMapping(value = "/{provaId}/delete")
	public String delete(@PathVariable("provaId") String provaId) {
		this.provaService.delete(provaId);
		
		return "redirect:/prove/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("prova", new Prova());
		
		return "prove/form";
	}

	@GetMapping(value="/{provaId}/edit")
	public String edit(@PathVariable("provaId") String provaId, 
			Model uiModel) {
		
		Prova p = this.provaService.findById(provaId);
		uiModel.addAttribute("prova", p);
		
		return "prove/form";
	}

	private void sanitizeId(String provaId) {
		if (provaId.contains("/")) {
			throw new RuntimeException("Id prova malformato");
		}
	}
	
	@PostMapping(value="/save")
	public String save(@ModelAttribute("prova") Prova prova, 
			BindingResult br, Model uiModel) {
		
		try {
			this.sanitizeId(prova.getProvaId());
			
			this.provaService.update(prova);
			
			String strMessage = "Prova (" + prova.getProvaId() + "," + prova.getTitle() +"," + prova.getDescription() + ") salvato correttamente";
			//uiModel.addAttribute("message", strMessage);
			
			return "redirect:/prove/list?message=" + strMessage;
		} catch (RuntimeException e) {
			return "redirect:/prove/list?message=" + e.getMessage();
		}
	}
	
	@Autowired
	public void setProvaService(ProvaService provaService) {
		this.provaService = provaService;
	}
}
