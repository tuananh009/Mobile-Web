package bkap.happyshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bkap.happyshop.dao.OrderDAO;
import bkap.happyshop.dao.ProductDAO;
import bkap.happyshop.dao.UserDAO;
import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.Product;
import bkap.happyshop.entity.User;

@Controller
public class AdminHomeController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping("/admin/home/index")
	public String index(Model model) {
		
		//Thong ke so luong nguoi dung
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("user", userDAO.findAll());
		
		//Thong ke so luong san pham
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("product", productDAO.findAll());
		
		//Thong ke so don hang
		Order order =  new Order();
		model.addAttribute("order", order);
		model.addAttribute("order", orderDAO.findAll());
		
		return "admin/home/index";
	}
}
