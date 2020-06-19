package by.lifetech.ishop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.lifetech.ishop.controller.exception.ControllerRuntimeException;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.exception.ServiceException;

@Controller
@RequestMapping("/")
public class WebController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/showMain")
	public String mainPageCommand(Model theModel, HttpSession session) {

		try {

			if (session.getAttribute("categories") == null) {
				session.setAttribute("categories", itemService.getCategories());
			}

			// theModel.addAttribute("categoryList", itemService.getCategories());

		} catch (ServiceException e) {
			throw new ControllerRuntimeException(e);
		}

		return "main";
	}

	@RequestMapping("/showCart")
	public String cartPageCommand(Model theModel) {

		return "cart";
	}

	@RequestMapping("/showCatalog")
	public String catalogPageCommand(Model theModel) {

		return "catalog";
	}
	
	
	@RequestMapping("/showItems")
	public String getItemsByCategory(Model theModel, @RequestParam("categoryId") int categoryId) {

		try {
			List<Item> items = itemService.getItemsByCategory(categoryId);
			System.out.println(items);
			theModel.addAttribute("itemList", items);

		} catch (ServiceException e) {
			throw new ControllerRuntimeException(e);
		}

		return "items";
	}
	
	
	@RequestMapping("/changeLocale")
	public String changeLocale(HttpSession session, @RequestParam("local") String local) {

		session.setAttribute("local", local);
		System.out.println("*********" + local + " --- "  + session.getAttribute("lastRequest").toString());
		
		if (session.getAttribute("lastRequest") != null)
        {
            return "redirect:.../" + session.getAttribute("lastRequest").toString();
        }
        else {
        	return "redirect:/showMain";
        }
		
		
		
	}

}
