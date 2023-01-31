package bkap.happyshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bkap.happyshop.dao.OrderDAO;
import bkap.happyshop.dao.OrderDetailDAO;
import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.OrderDetail;

@Controller
public class OrderManagerController {
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@RequestMapping("/admin/order/index")
	public String index(Model m) {
		Order entity = new Order();
		m.addAttribute("entity", entity);
		m.addAttribute("details", orderDetailDAO.findByOrder(entity));
		m.addAttribute("list", orderDAO.findAll());
		return "admin/order/index";
	}
	
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model m,@PathVariable("id") Integer id) {
		Order entity = orderDAO.findById(id);
		m.addAttribute("entity", entity);
		return "admin/order/_form";
	}
	
	@RequestMapping("/admin/order/create")
	public String create(RedirectAttributes m,@ModelAttribute("entity") Order entity) {
		orderDAO.create(entity);
		m.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/update")
	public String update(RedirectAttributes m,@ModelAttribute("entity") Order entity) {
		boolean isOk = orderDAO.update(entity);
		if (isOk) {
			m.addAttribute("message", "Cập nhật đơn hàng thành công!");
		}else {
			m.addAttribute("message", "Cập nhật thất bại!");
		}
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping(value = {"/admin/order/delete","/admin/order/delete/{id}"})
	public String delete(Model m,@PathVariable(value ="id", required = false) Integer orderId) {
		orderDetailDAO.deleteByOrderId(orderId);
		boolean isOk = orderDAO.delete(orderId);
		if (isOk) {
			m.addAttribute("message", "Xóa thành công!");
		}else {
			m.addAttribute("message", "Xóa thất bại");
		}
		Order entity = new Order();
		m.addAttribute("details", orderDetailDAO.findByOrder(entity));
		m.addAttribute("list", orderDAO.findAll());
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/_details/{id}")
	public String details(@PathVariable(name ="id") Integer id, Model m) {
		Order order = orderDAO.findById(id);
		List<OrderDetail> details = orderDetailDAO.findByOrder(order);
		m.addAttribute("order",order);
		m.addAttribute("details", details);
		return "admin/order/_details";
	}

	
}
