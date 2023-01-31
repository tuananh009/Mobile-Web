package bkap.happyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bkap.happyshop.dao.ProductDAO;
import bkap.happyshop.entity.Product;

@Controller
public class HomeController {
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value = {"","/home"})
	public String index(Model m) {
		List<Product> list2 = productDAO.findBySpecial(4);
		m.addAttribute("list",list2);
		List<Product> list3 = productDAO.findBySpecial(0);
		m.addAttribute("list1", list3);
		return "home/index";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "home/about";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "home/contact";
	}
	@RequestMapping("/feedback")
	public String feedback() {
		return "home/feedback";
	}
	@RequestMapping("/faq")
	public String faq() {
		return "home/faq";
	}
}
