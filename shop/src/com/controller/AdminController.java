package com.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Admins;
import com.entity.Goods;
import com.entity.Tops;
import com.entity.Types;
import com.entity.Users;
import com.service.AdminService;
import com.service.GoodService;
import com.service.OrderService;
import com.service.SkuService;
import com.service.TopService;
import com.service.TypeService;
import com.service.UserService;
import com.util.PageUtil;
import com.util.SafeUtil;
import com.util.UploadUtil;

/**
 * Background related interface
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final int rows = 10;

	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodService goodService;
	@Autowired
	private TopService topService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private SkuService skuService;

	/**
	 * Administrator login
	 * @return
	 */
	@GetMapping("/login")
	public String log() {
		return "/admin/login.jsp";
	}
	
	/**
	 * Administrator login
	 * @return
	 */
	@PostMapping("/login")
	public String login(Admins admin, HttpServletRequest request, HttpSession session) {
		if (adminService.checkUser(admin.getUsername(), admin.getPassword())) {
			session.setAttribute("username", admin.getUsername());
			return "redirect:typeList";
		}
		request.setAttribute("msg", "Wrong user name or password!");
		return "/admin/login.jsp";
	}

	/**
	 * Sign out
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "/admin/login.jsp";
	}
	
	/**
	 * Backend server homepage
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		request.setAttribute("msg", "Successful login");
		return "/admin/index.jsp";
	}

	/**
	 * Category list
	 * 
	 * @return
	 */
	@RequestMapping("/typeList")
	public String typeList(HttpServletRequest request) {
		request.setAttribute("flag", 1);
		request.setAttribute("typeList", typeService.getList());
		return "/admin/type_list.jsp";
	}

	/**
	 * Add category
	 * 
	 * @return
	 */
	@RequestMapping("/typeAdd")
	public String typeAdd(HttpServletRequest request) {
		request.setAttribute("flag", 1);
		return "/admin/type_add.jsp";
	}
	
	/**
	 * Add category
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/typeSave")
	public String typeSave(Types type, MultipartFile file,
			@RequestParam(required=false, defaultValue="1") int page) throws Exception {
		type.setCover(UploadUtil.upload(file));
		typeService.add(type);
		return "redirect:typeList?flag=1&page="+page;
	}

	/**
	 * Update category
	 * 
	 * @return
	 */
	@RequestMapping("/typeEdit")
	public String typeEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 1);
		request.setAttribute("type", typeService.get(id));
		return "/admin/type_edit.jsp";
	}

	/**
	 * Update category
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/typeUpdate")
	public String typeUpdate(Types type, MultipartFile file,
			@RequestParam(required=false, defaultValue="1") int page) throws Exception {
		if (Objects.nonNull(file) && !file.isEmpty()) {
			type.setCover(UploadUtil.upload(file));
		}
		typeService.update(type);
		return "redirect:typeList?flag=1&page="+page;
	}

	/**
	 * Delete category
	 * 
	 * @return
	 */
	@RequestMapping("/typeDelete")
	public String typeDelete(Types type, 
			@RequestParam(required=false, defaultValue="1") int page) {
		typeService.delete(type);
		return "redirect:typeList?flag=1&page="+page;
	}

	/**
	 * sku list
	 * 
	 * @return
	 */
	@RequestMapping("/skuList")
	public String skuList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request) {
		request.setAttribute("flag", 2);
		request.setAttribute("status", status);
		request.setAttribute("skuList", status>0 ? skuService.getSizeList() : skuService.getColorList());
		return "/admin/sku_list.jsp";
	}
	
	/**
	 * add sku
	 * 
	 * @return
	 */
	@RequestMapping("/skuSave")
	public String skuSave(String name, int status) {
		if(status > 0) {
			skuService.addSize(name);
		}else {
			skuService.addColor(name);
		}
		return "redirect:skuList?status="+status;
	}
	
	/**
	 * delete sku
	 * 
	 * @return
	 */
	@RequestMapping("/skuDelete")
	public String skuDelete(int id, int status) {
		if(status > 0) {
			skuService.deleteSize(id);
		}else {
			skuService.deleteColor(id);
		}
		return "redirect:skuList?status="+status;
	}
	
	
	/**
	 * Product List
	 * 
	 * @return
	 */
	@RequestMapping("/goodList")
	public String goodList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 3);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		request.setAttribute("goodList", goodService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, goodService.getTotal(status), page, rows));
		return "/admin/good_list.jsp";
	}

	/**
	 * Add product
	 * 
	 * @return
	 */
	@RequestMapping("/goodAdd")
	public String goodAdd(HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("colorList", skuService.getColorList());
		request.setAttribute("sizeList", skuService.getSizeList());
		return "/admin/good_add.jsp";
	}

	/**
	 * Add product
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodSave")
	public String goodSave(Goods good, MultipartFile file,
			@RequestParam(required=false, defaultValue="1") int page) throws Exception {
		good.setCover(UploadUtil.upload(file));
		goodService.add(good);
		return "redirect:goodList?flag=3&page="+page;
	}

	/**
	 * Update product
	 * 
	 * @return
	 */
	@RequestMapping("/goodEdit")
	public String goodEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 3);
		request.setAttribute("typeList", typeService.getList());
		request.setAttribute("colorList", skuService.getColorList());
		request.setAttribute("sizeList", skuService.getSizeList());
		request.setAttribute("good", goodService.get(id));
		return "/admin/good_edit.jsp";
	}

	/**
	 * Update product
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/goodUpdate")
	public String goodUpdate(Goods good, MultipartFile file, 
			@RequestParam(required=false, defaultValue="1") int page) throws Exception {
		if (Objects.nonNull(file) && !file.isEmpty()) {
			good.setCover(UploadUtil.upload(file));
		}
		goodService.update(good);
		return "redirect:goodList?flag=3&page="+page;
	}

	/**
	 * Delete product
	 * 
	 * @return
	 */
	@RequestMapping("/goodDelete")
	public String goodDelete(int id, 
			@RequestParam(required=false, defaultValue="1") int page) {
		goodService.delete(id);
		return "redirect:goodList?flag=3&page="+page;
	}
	
	/**
	 * Add recommendation
	 * @return
	 */
	@RequestMapping("/topSave")
	public @ResponseBody String topSave(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		int id = topService.add(tops);
		return id > 0 ? "ok" : null;
	}
	
	/**
	 * Delete recommendation
	 * @return
	 */
	@RequestMapping("/topDelete")
	public @ResponseBody String topDelete(Tops tops, 
			@RequestParam(required=false, defaultValue="0")byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		boolean flag = topService.delete(tops);
		return flag ? "ok" : null;
	}

	/**
	 * Order List
	 * 
	 * @return
	 */
	@RequestMapping("/orderList")
	public String orderList(@RequestParam(required=false, defaultValue="0")byte status, HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 4);
		request.setAttribute("status", status);
		request.setAttribute("orderList", orderService.getList(status, page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, orderService.getTotal(status), page, rows));
		return "/admin/order_list.jsp";
	}

	/**
	 * Order shipping
	 * 
	 * @return
	 */
	@RequestMapping("/orderDispose")
	public String orderDispose(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.dispose(id);
		return "redirect:orderList?flag=4&status="+status+"&page="+page;
	}
	
	/**
	 * Order completed
	 * 
	 * @return
	 */
	@RequestMapping("/orderFinish")
	public String orderFinish(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.finish(id);
		return "redirect:orderList?flag=4&status="+status+"&page="+page;
	}

	/**
	 * Order delete
	 * 
	 * @return
	 */
	@RequestMapping("/orderDelete")
	public String orderDelete(int id, byte status,
			@RequestParam(required=false, defaultValue="1") int page) {
		orderService.delete(id);
		return "redirect:orderList?flag=4&status="+status+"&page="+page;
	}

	/**
	 * Customer management
	 * 
	 * @return
	 */
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 5);
		request.setAttribute("userList", userService.getList(page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, userService.getTotal(), page, rows));
		return "/admin/user_list.jsp";
	}

	/**
	 * Add customer
	 * 
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(HttpServletRequest request) {
		request.setAttribute("flag", 5);
		return "/admin/user_add.jsp";
	}

	/**
	 * Add customer
	 * 
	 * @return
	 */
	@RequestMapping("/userSave")
	public String userSave(Users user, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		if (userService.isExist(user.getUsername())) {
			request.setAttribute("msg", "Username already exists!");
			return "/admin/user_add.jsp";
		}
		userService.add(user);
		return "redirect:userList?flag=5&page="+page;
	}

	/**
	 * Customer password reset page
	 * 
	 * @return
	 */
	@RequestMapping("/userRe")
	public String userRe(int id, HttpServletRequest request) {
		request.setAttribute("flag", 5);
		request.setAttribute("user", userService.get(id));
		return "/admin/user_reset.jsp";
	}

	/**
	 * Customer password reset
	 * 
	 * @return
	 */
	@RequestMapping("/userReset")
	public String userReset(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		String password = SafeUtil.encode(user.getPassword());
		user = userService.get(user.getId());
		user.setPassword(password);
		userService.update(user);
		return "redirect:userList?page="+page;
	}

	/**
	 * Customer update
	 * 
	 * @return
	 */
	@RequestMapping("/userEdit")
	public String userEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 5);
		request.setAttribute("user", userService.get(id));
		return "/admin/user_edit.jsp";
	}

	/**
	 * Customer update
	 * 
	 * @return
	 */
	@RequestMapping("/userUpdate")
	public String userUpdate(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		userService.update(user);
		return "redirect:userList?flag=5&page="+page;
	}

	/**
	 * Delete customer
	 * 
	 * @return
	 */
	@RequestMapping("/userDelete")
	public String userDelete(Users user, 
			@RequestParam(required=false, defaultValue="1") int page) {
		userService.delete(user);
		return "redirect:userList?flag=5&page="+page;
	}

	/**
	 * Administrator list
	 * 
	 * @return
	 */
	@RequestMapping("/adminList")
	public String adminList(HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		request.setAttribute("flag", 6);
		request.setAttribute("adminList", adminService.getList(page, rows));
		request.setAttribute("pageTool", PageUtil.getPageTool(request, adminService.getTotal(), page, rows));
		return "/admin/admin_list.jsp";
	}

	/**
	 * Add Administrator
	 * 
	 * @return
	 */
	@RequestMapping("/adminAdd")
	public String adminAdd(HttpServletRequest request) {
		request.setAttribute("flag", 6);
		return "/admin/admin_add.jsp";
	}
	
	/**
	 * Administrator password reset
	 * 
	 * @return
	 */
	@RequestMapping("/adminRe")
	public String adminRe(int id, HttpServletRequest request) {
		request.setAttribute("flag", 6);
		request.setAttribute("admin", adminService.get(id));
		return "/admin/admin_reset.jsp";
	}

	/**
	 * Administrator password reset
	 * 
	 * @return
	 */
	@RequestMapping("/adminReset")
	public String adminReset(Admins admin, HttpServletRequest request,
			@RequestParam(required=false, defaultValue="1") int page) {
		String password = SafeUtil.encode(admin.getPassword());
		admin = adminService.get(admin.getId());
		admin.setPassword(password);
		adminService.update(admin);
		return "redirect:adminList?page="+page;
	}

	/**
	 * Add Administrator
	 * 
	 * @return
	 */
	@RequestMapping("/adminSave")
	public String adminSave(Admins admin, HttpServletRequest request, 
			@RequestParam(required=false, defaultValue="1") int page) {
		if (adminService.isExist(admin.getUsername())) {
			request.setAttribute("msg", "用户名已存在!");
			return "/admin/admin_add.jsp";
		}
		adminService.add(admin);
		return "redirect:adminList?flag=6&page="+page;
	}

	/**
	 * Modified administrator
	 * 
	 * @return
	 */
	@RequestMapping("/adminEdit")
	public String adminEdit(int id, HttpServletRequest request) {
		request.setAttribute("flag", 6);
		request.setAttribute("admin", adminService.get(id));
		return "/admin/admin_edit.jsp";
	}

	/**
	 * Update administrator
	 * 
	 * @return
	 */
	@RequestMapping("/adminUpdate")
	public String adminUpdate(Admins admin, 
			@RequestParam(required=false, defaultValue="1") int page) {
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		adminService.update(admin);
		return "redirect:adminList?flag=6&page="+page;
	}

	/**
	 * Delete administrator
	 * 
	 * @return
	 */
	@RequestMapping("/adminDelete")
	public String adminDelete(Admins admin, 
			@RequestParam(required=false, defaultValue="1") int page) {
		adminService.delete(admin);
		return "redirect:adminList?flag=6&page="+page;
	}

}
