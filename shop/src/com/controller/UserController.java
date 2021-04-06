package com.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Orders;
import com.entity.Shopcart;
import com.entity.Users;
import com.service.GoodService;
import com.service.OrderService;
import com.service.ShopcartService;
import com.service.SkuService;
import com.service.TypeService;
import com.service.UserService;
import com.util.SafeUtil;

/**
 * User related interface
 */
@Controller
@RequestMapping("/index")
public class UserController{
	
	@Resource
	private UserService userService;
	@Resource
	private OrderService orderService;
	@Resource
	private GoodService goodService;
	@Resource
	private TypeService typeService;
	@Resource
	private ShopcartService shopcartService;
	@Resource
	private SkuService skuService;

	
	/**
	 * User registration
	 * @return
	 */
	@GetMapping("/register")
	public String reg(Model model) {
		model.addAttribute("flag", -1); // Registration page
		return "/index/register.jsp";
	}
	
	/**
	 * User registration
	 * @return
	 */
	@PostMapping("/register")
	public String register(Users user, Model model){
		if (user.getUsername().isEmpty()) {
			model.addAttribute("msg", "Username can not be empty!");
			return "/index/register.jsp";
		}else if (userService.isExist(user.getUsername())) {
			model.addAttribute("msg", "Username already exists!");
			return "/index/register.jsp";
		}else {
			String password = user.getPassword();
			userService.add(user);
			user.setPassword(password);
			return "/index/index"; // Go to login after successful registration
		}
	}
	
	/**
	 * User login
	 * @return
	 */
	@GetMapping("/login")
	public String log() {
		return "/index/index";
	}
	
	/**
	 * User login
	 * @return
	 */
	@PostMapping("/login")
	public String login(@RequestParam(required=false, defaultValue="0")int flag, Users user, HttpSession session, Model model) {
		model.addAttribute("typeList", typeService.getList());
		if(flag==-1) {
			flag = 6; // Login page
			return "/index/index";
		}
		if(userService.checkUser(user.getUsername(), user.getPassword())){
			Users loginUser = userService.get(user.getUsername());
			session.setAttribute("user", loginUser);
			// Restore shopping cart quantity
			session.setAttribute("total", shopcartService.getTotal(loginUser.getId()));
			return "redirect:index";
		} else {
			model.addAttribute("msg", "Wrong user name or password!");
			return "/index/index";
		}
	}

	/**
	 * Logout
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("order");
		return "/index/index";
	}
	
	/**
	 * Check the shopping cart
	 * @return
	 */
	@RequestMapping("/shopcart")
	public String shopcart(Model model, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("msg", "Please log in first!");
			return "/index/index";
		}
		model.addAttribute("typeList", typeService.getList());
		model.addAttribute("shopcartList", shopcartService.getList(user.getId()));
		model.addAttribute("totalPrice", shopcartService.getTotalPrice(user.getId()));
		return "/index/shopcart.jsp";
	}
	
	/**
	 * Buy
	 * @return
	 */
	@RequestMapping("/buy")
	public @ResponseBody int buy(Shopcart shopcart, HttpSession session, Model model){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return -111;
		}
		shopcart.setUserId(user.getId());
		shopcart.setGood(goodService.get(shopcart.getGoodId()));
		// Verify inventory
//		int stock = skuService.getStock(shopcart.getGoodId(), shopcart.getColorId(), shopcart.getSizeId());
//		if(shopcart.getAmount() > stock) {
//			model.addAttribute("msg", "product [ " + shopcart.getGood().getName() + " ] insufficient stock! Current stock only: " + stock);
//		}
		return orderService.save(Arrays.asList(shopcart), user);
	}
	
	/**
	 * Buy
	 * @return
	 */
	@RequestMapping("/cart")
	public @ResponseBody int cart(Shopcart shopcart, HttpSession session, Model model){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return -111;
		}
		shopcart.setUserId(user.getId());
		shopcartService.save(shopcart);
		int total = shopcartService.getTotal(user.getId());
		session.setAttribute("total", total);
		return total;
	}
	
	/**
	 * Add number
	 */
	@RequestMapping("/add")
	public @ResponseBody boolean add(int skuid, HttpSession session){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return shopcartService.add(skuid);
	}
	
	/**
	 * Less number
	 */
	@RequestMapping("/less")
	public @ResponseBody boolean less(int skuid, HttpSession session){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return shopcartService.less(skuid);
	}
	
	/**
	 * Delete product
	 */
	@RequestMapping("/delete")
	public @ResponseBody boolean delete(int skuid, HttpSession session){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		shopcartService.delete(skuid);
		session.setAttribute("total", shopcartService.getTotal(user.getId()));
		return true;
	}
	
	/**
	 * Total amount
	 * @return
	 */
	@RequestMapping("/total")
	public @ResponseBody int total(HttpSession session){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return -111;
		}
		return shopcartService.getTotalPrice(user.getId());
	}
	
	
	/**
	 * Submit Order
	 * @return
	 */
	@RequestMapping("/save")
	public String save(ServletRequest request, HttpSession session, Model model){
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("msg", "Please login first");
			return "/index/index";
		}
		List<Shopcart> shopcartList = shopcartService.getList(user.getId());
		if(Objects.isNull(shopcartList) || shopcartList.isEmpty()) {
			model.addAttribute("msg", "Shopping cart is empty");
			return shopcart(model, session);
		}
		// Check inventory
		for(Shopcart cart : shopcartList) {
			int stock = skuService.getStock(cart.getGoodId(), cart.getColorId(), cart.getSizeId());
			if(cart.getAmount() > stock) {
				model.addAttribute("msg", "Product [ " + cart.getGood().getName() + " ] insufficient stock! Current stock only:  " + stock);
				return shopcart(model, session);
			}
		}
		int orderid = orderService.save(shopcartList, user);
		if(orderid > 0) {
			// Empty shopping cart
			session.setAttribute("total", shopcartService.getTotal(user.getId()));
			// Jump to payment
			return "redirect:topay?orderid="+orderid;
		} 

		model.addAttribute("msg", "Something went wrong");
		return shopcart(model, session);
	}
	
	/**
	 * Payment page
	 * @return
	 */
	@RequestMapping("/topay")
	public String topay(int orderid, ServletRequest request, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Please login first!");
			return "/index/index";
		}
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("order", orderService.get(orderid));
		return "/index/pay.jsp";
	}
	
	/**
	 * Payment (simulation)
	 * @return
	 */
	@RequestMapping("/pay")
	public String pay(Orders order, ServletRequest request, HttpSession session) {
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			request.setAttribute("msg", "Please login first!");
			return "/index/index";
		}
		// Simulated payment
		orderService.pay(order);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("order", orderService.get(order.getId()));
		request.setAttribute("msg", "The payment is successful! return to the order list");
		return "/index/pay.jsp";
	}
	
	/**
	 * Check order list
	 * @return
	 */
	@RequestMapping("/order")
	public String order(HttpSession session, Model model){
		model.addAttribute("flag", 12);
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("msg", "Please login to check order list");
			return "/index/index";
		}
		model.addAttribute("typeList", typeService.getList());
		model.addAttribute("orderList", orderService.getListByUserid(user.getId()));
		return "/index/order.jsp";
	}
	
	
	/**
	 * Personal information
	 * @return
	 */
	@RequestMapping("/my")
	public String my(HttpSession session, Model model){
		model.addAttribute("flag", 11);
		model.addAttribute("typeList", typeService.getList());
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("msg", "Please login first!");
			return "/index/index";
		}
		model.addAttribute("user", user);
		return "/index/my.jsp";
	}
	
	
	/**
	 * Change personal information
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(Users user, HttpSession session, Model model){
		model.addAttribute("flag", 11);
		model.addAttribute("typeList", typeService.getList());
		Users userLogin = (Users) session.getAttribute("user");
		if (userLogin == null) {
			model.addAttribute("msg", "Please login first!");
			return "/index/index";
		}
		// Change information
		Users u = userService.get(userLogin.getId());
		u.setName(user.getName());
		u.setPhone(user.getPhone());
		u.setAddress(user.getAddress());
		userService.update(u);  // Update database
		session.setAttribute("user", u); // Update session
		model.addAttribute("msg", "Information modified successfully!");
		return "/index/my.jsp";
	}
	
	
	/**
	 * Change user password
	 * @return
	 */
	@RequestMapping("/updatePassword")
	public String updatePassword(Users user, HttpSession session, Model model){
		model.addAttribute("flag", 11);
		model.addAttribute("typeList", typeService.getList());
		Users userLogin = (Users) session.getAttribute("user");
		if (userLogin == null) {
			model.addAttribute("msg", "Please login firts!");
			return "/index/index";
		}
		// Change password
		Users u = userService.get(userLogin.getId());
		if(user.getPasswordNew()!=null && !user.getPasswordNew().trim().isEmpty()) {
			if (user.getPassword()!=null && !user.getPassword().trim().isEmpty() 
					&& SafeUtil.encode(user.getPassword()).equals(u.getPassword())) {
				if (user.getPasswordNew()!=null && !user.getPasswordNew().trim().isEmpty()) {
					u.setPassword(SafeUtil.encode(user.getPasswordNew()));
				}
				userService.update(u);  // Update database
				session.setAttribute("user", u); // Update session
				model.addAttribute("msg", "Password have been successfully changed!");
				return "redirect:logout";
			}else {
				model.addAttribute("msg", "The original password is wrong!");
			}
		}
		return "/index/index";
	}
	
}