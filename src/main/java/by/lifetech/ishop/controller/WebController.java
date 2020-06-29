package by.lifetech.ishop.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.lifetech.ishop.controller.exception.ControllerRuntimeException;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;

@Controller
@RequestMapping("/")
public class WebController {

	private ItemService itemService;
	private UserService userService;
	
	
	@Autowired
	public WebController(ItemService itemService, UserService userService) {
		super();
		this.itemService = itemService;
		this.userService = userService;
	}


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
	
	
	@RequestMapping("/showItemInfo")
	public String getItemById(Model theModel, @RequestParam("itemId") int itemId) {

		try {
			Item item = itemService.getItem(itemId);
			theModel.addAttribute("item", item);
		} catch (ServiceException e) {
			throw new ControllerRuntimeException(e);
		}
		return "itemInfo";
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
	
	
	@PostMapping(value="/changeLocale")
	public String changeLocal(HttpSession session, @RequestParam("local") String local) {

		System.out.println("test");
		session.setAttribute("local", local);
		
		if (session.getAttribute("lastRequest") != null)
        {
            return "redirect:" + session.getAttribute("lastRequest").toString();
        }
        else {
        	return "redirect:/showMain";
        }
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goLogin(){
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String goLogout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		return "redirect:/showMain";
	}
	
	
	@RequestMapping(value="/showRegister")
	public String goRegistrationPage(Model theModel){
		theModel.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value="/registerProcess", method = RequestMethod.POST)
	public String registerProcess(RedirectAttributes redirectAttributes, @ModelAttribute("user") User user){
		
		try {
			userService.registration(user);
			redirectAttributes.addAttribute("register", "success");
		} catch (ServiceException e) {
			throw new ControllerRuntimeException(e);
		}
		
		return "redirect:/showMain";
	}
	
	
	
	@PostMapping(value="/addReview")
	public String addReview(HttpSession session, Principal principal, 
			@RequestParam("itemId") int itemId,
			@RequestParam("comment") String comment,
			@RequestParam("rating") byte rating) {

		System.out.println("******* comment: " + comment);
		
		try {
			User currentUser = userService.getUserByLogin(principal.getName());
			System.out.println("!!!!!!! " + currentUser);
			
			itemService.addItemReview(currentUser, itemId, rating, comment);
		} catch (ServiceException e) {
			new ControllerRuntimeException(e);
		}
		
		
		return "redirect:" + session.getAttribute("lastRequest").toString();
	}

}
