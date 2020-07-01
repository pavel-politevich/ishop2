package by.lifetech.ishop.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

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
import by.lifetech.ishop.dao.impl.ItemDAOImpl;
import by.lifetech.ishop.entity.Item;
import by.lifetech.ishop.entity.User;
import by.lifetech.ishop.service.ItemService;
import by.lifetech.ishop.service.OrderService;
import by.lifetech.ishop.service.UserService;
import by.lifetech.ishop.service.exception.ServiceException;

@Controller
@RequestMapping("/")
public class WebController {

	private static final String CONFIRM_ORDER_SUCCESS_VAL = "ok";

	private static final String CONFIRM_ATTR = "confirm";

	private static final String PAYMENT_TYPE_ID_ATTR = "paymentType";

	private static final String ADDRESS_ATTR = "address";

	private static final String COUNT_ATTR = "count";

	private static final String RATING_ATTR = "rating";

	private static final String COMMENT_ATTR = "comment";

	private static final String RESULT_SUCCESS_VALUE = "success";

	private static final String REGISTER_ATTR = "register";

	private static final String USER_ATTR = "user";

	private static final String LAST_REQUEST_ATTR = "lastRequest";

	private static final String LOCAL_ATTR = "local";

	private static final String ITEM_LIST_ATTR = "itemList";

	private static final String CATEGORY_ID_ATTR = "categoryId";

	private static final String ITEM_ATTR = "item";

	private static final String ITEM_ID_ATTR = "itemId";

	private static final String PAYMENT_TYPES_ATTR = "paymentTypes";

	private static final String ORDER_ATTR = "order";

	private static final String ORDER_ID_ATTR = "orderId";

	private static final String CATEGORIES_ATTR = "categories";
	
	private ItemService itemService;
	private UserService userService;
	private OrderService orderService;
	
	private static final Logger logger = Logger.getLogger(ItemDAOImpl.class);
	
	
	@Autowired
	public WebController(ItemService itemService, UserService userService, OrderService orderService) {
		super();
		this.itemService = itemService;
		this.userService = userService;
		this.orderService = orderService;
	}


	@RequestMapping("/showMain")
	public String mainPageCommand(Model theModel, HttpSession session) {

		try {

			if (session.getAttribute(CATEGORIES_ATTR) == null) {
				session.setAttribute(CATEGORIES_ATTR, itemService.getCategories());
			}

		} catch (ServiceException e) {
			logger.error("Error while show main page");
			throw new ControllerRuntimeException(e);
		}

		return "main";
	}

	@RequestMapping("/showCart")
	public String cartPageCommand(HttpSession session, Model theModel) {
		
		if(session.getAttribute(ORDER_ID_ATTR) != null) {
			int orderId = (int) session.getAttribute(ORDER_ID_ATTR);
			
			try {
				theModel.addAttribute(ORDER_ATTR, orderService.getOrder(orderId));
				theModel.addAttribute(PAYMENT_TYPES_ATTR, orderService.getPaymentTypes());
			} catch (ServiceException e) {
				logger.error("Error while show cart page");
				throw new ControllerRuntimeException(e);
			}
		}

		return "cart";
	}

	@RequestMapping("/showCatalog")
	public String catalogPageCommand(Model theModel) {
		return "catalog";
	}
	
	@RequestMapping("/errors")
	public String showErrorPage() {
		return "errorPage";
	}
	
	
	@RequestMapping("/showItemInfo")
	public String getItemById(Model theModel, @RequestParam(ITEM_ID_ATTR) int itemId) {

		try {
			Item item = itemService.getItem(itemId);
			theModel.addAttribute(ITEM_ATTR, item);
		} catch (ServiceException e) {
			logger.error("Error while show item info page");
			throw new ControllerRuntimeException(e);
		}
		return "itemInfo";
	}
	
	
	@RequestMapping("/showItems")
	public String getItemsByCategory(Model theModel, @RequestParam(CATEGORY_ID_ATTR) int categoryId) {

		try {
			List<Item> items = itemService.getItemsByCategory(categoryId);
			theModel.addAttribute(ITEM_LIST_ATTR, items);

		} catch (ServiceException e) {
			logger.error("Error while show items page");
			throw new ControllerRuntimeException(e);
		}

		return "items";
	}
	
	
	@PostMapping(value="/changeLocale")
	public String changeLocal(HttpSession session, @RequestParam(LOCAL_ATTR) String local) {
		session.setAttribute(LOCAL_ATTR, local);
		
		if (session.getAttribute(LAST_REQUEST_ATTR) != null)
        {
            return "redirect:" + session.getAttribute(LAST_REQUEST_ATTR).toString();
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
		theModel.addAttribute(USER_ATTR, new User());
		return "registration";
	}
	
	@RequestMapping(value="/registerProcess", method = RequestMethod.POST)
	public String registerProcess(RedirectAttributes redirectAttributes, @ModelAttribute(USER_ATTR) User user){
		try {
			userService.registration(user);
			redirectAttributes.addAttribute(REGISTER_ATTR, RESULT_SUCCESS_VALUE);
		} catch (ServiceException e) {
			logger.error("Error while registration");
			throw new ControllerRuntimeException(e);
		}
		
		return "redirect:/showMain";
	}
	
	
	
	@PostMapping(value="/addReview")
	public String addReview(HttpSession session, Principal principal, 
			@RequestParam(ITEM_ID_ATTR) int itemId,
			@RequestParam(COMMENT_ATTR) String comment,
			@RequestParam(RATING_ATTR) byte rating) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		
		try {
			User currentUser = userService.getUserByLogin(principal.getName());		
			itemService.addItemReview(currentUser, itemId, rating, comment);
			
		} catch (ServiceException e) {
			logger.error("Error while add review");
			new ControllerRuntimeException(e);
		}
		
		return "redirect:" + session.getAttribute(LAST_REQUEST_ATTR).toString();
	}
	
	
	
	@PostMapping(value="/addToCart")
	public String addToCart(HttpSession session, Principal principal, 
			@RequestParam(ITEM_ID_ATTR) int itemId,
			@RequestParam(COUNT_ATTR) int count) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		
		try {
			
			User currentUser = userService.getUserByLogin(principal.getName());
			int orderId;
			
			if(session.getAttribute(ORDER_ID_ATTR) == null) {
				orderId = orderService.createEmptyOrder(currentUser.getUserId());
				session.setAttribute(ORDER_ID_ATTR, orderId);
			}
			else {
				orderId = (int) session.getAttribute(ORDER_ID_ATTR);
			}
			
			orderService.addItem(orderId, itemId, count);
			
		} catch (ServiceException e) {
			logger.error("Error while add to cart");
			new ControllerRuntimeException(e);
		}
		
		return "redirect:" + session.getAttribute(LAST_REQUEST_ATTR).toString();
	}
	
	
	@PostMapping(value="/deleteFromCart")
	public String delFromCart(HttpSession session, Principal principal, Model theModel,
			@RequestParam(ITEM_ID_ATTR) int itemId) {
		
		if(principal == null) {
			return "redirect:/login";
		}
				
		try {
			int orderId = (int) session.getAttribute(ORDER_ID_ATTR);
			orderService.deleteItem(orderId, itemId);
						
		} catch (ServiceException e) {
			logger.error("Error while delete from cart");
			new ControllerRuntimeException(e);
		}
		
		return "redirect:/showCart";
	}
	
	
	
	@PostMapping(value="/confirmOrder")
	public String confirmOrder(RedirectAttributes redirectAttributes, HttpSession session, Principal principal,
			@RequestParam(COMMENT_ATTR) String comment,
			@RequestParam(ADDRESS_ATTR) String address,
			@RequestParam(PAYMENT_TYPE_ID_ATTR) int paymentType) {
		
		if(principal == null) {
			return "redirect:/login";
		}
				
		try {
			int orderId = (int) session.getAttribute(ORDER_ID_ATTR);
			orderService.confirmOrder(orderId, comment, address, paymentType);
			session.removeAttribute(ORDER_ID_ATTR);
			
			redirectAttributes.addAttribute(CONFIRM_ATTR, CONFIRM_ORDER_SUCCESS_VAL);
			redirectAttributes.addAttribute(ORDER_ID_ATTR, orderId);

		} catch (ServiceException e) {
			logger.error("Error while confirm order");
			new ControllerRuntimeException(e);
		}
		
		return "redirect:/showCart";
	}

}
