/*package it.univpm.hhc.controller;

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

import it.univpm.hhc.model.entities.Item;
import it.univpm.hhc.services.ItemService;

@RequestMapping("/items")
@Controller
public class ItemController {
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private ItemService itemService;
	
	@GetMapping(value = "/list")
	public String list(@RequestParam(value = "message", required=false) String message, Model uiModel) {
		logger.info("Listing items");
		List<Item> allItems = this.itemService.findAll();
		
		uiModel.addAttribute("items", allItems);
		uiModel.addAttribute("numItems", allItems.size());
		
		// TODO ricevere un parametro via GET (es. per messaggio di esito operazione)
		uiModel.addAttribute("message", message);
		
		return "items/list";
	}
	
	@GetMapping(value = "/{itemId}/delete")
	public String delete(@PathVariable("itemId") String itemId) {
		this.itemService.delete(new Long(itemId));
		
		return "redirect:/items/list";
	}
	
	//NON FUNZIONA
	@GetMapping(value = "/add")
	public String add(Model uiModel) {
		
		uiModel.addAttribute("item", new Item());
		
		return "items/form";
	}

	@GetMapping(value="/{itemId}/edit")
	public String edit(@PathVariable("itemId") String itemId, 
			Model uiModel) {
		
		Item i = this.itemService.findById(new Long(itemId));
		uiModel.addAttribute("item", i);
		
		return "items/form";
	}
// serve qualora ho un id che non va bene e non uso autoincrement
//	private void sanitizeId(String itemId) {
//		if (itemId.contains("/")) {
//			throw new RuntimeException("Id item malformato");
//		}
//	}
//	
//	@PostMapping(value="/save")
//	public String save(@ModelAttribute("item") Item item, 
//			BindingResult br, Model uiModel) {
//		
//		try {
//			this.sanitizeId(item.getItemId());
//			
//			this.itemService.update(item);
//			
//			String strMessage = "Item (" + item.getItemId() + "," + item.getTitle() +"," + item.getDescription() + ") salvato correttamente";
//			//uiModel.addAttribute("message", strMessage);
//			
//			return "redirect:/items/list?message=" + strMessage;
//		} catch (RuntimeException e) {
//			return "redirect:/items/list?message=" + e.getMessage();
//		}
//	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newItem") Item newItem, BindingResult br) {
		this.itemService.update(newItem);
		
		return "redirect:/items/list/";
		
		// return "redirect:singers/list"; // NB questo non funzionerebbe!
		
	}
	
	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
}*/
