package bkap.happyshop.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bkap.happyshop.dao.CategoryDAO;
import bkap.happyshop.dao.ProductDAO;
import bkap.happyshop.entity.Product;

@Controller
public class ProductManagerController {
	@Autowired
	ProductDAO productDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ServletContext app;

	@RequestMapping("admin/product/index")
	public String index(Model m) {
		Product entity = new Product();
		m.addAttribute("entity", entity);
		entity.setProductDate(new Date());
		entity.setViewCount(0);
		m.addAttribute("list", productDAO.findAll());
		m.addAttribute("listCa", categoryDAO.findAll());
		return "admin/product/index";
	}

	@RequestMapping("/admin/product/create")
	public String create(RedirectAttributes m, @ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file) throws IllegalStateException, IOException {
		if (file.isEmpty()) {
			entity.setImage("iphone11_proMax.jpg");
		} else {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/products/" + entity.getImage());
			file.transferTo(new File(path));
		}
		productDAO.create(entity);
		m.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/product/index";
	}

	@RequestMapping(value = { "/admin/product/delete", "/admin/product/delete/{id}" })
	public String delete(Model m, @PathVariable(value = "id", required = false) Integer id) {
		boolean isOk = productDAO.delete(id);
		if (isOk) {
			m.addAttribute("message", "Xóa thành công!");
		} else {
			m.addAttribute("message", "Xóa Thất Bại!");
		}
		List<Product> list = productDAO.findAll();
		m.addAttribute("list", list);
		return "redirect:/admin/product/index";
	}

	@RequestMapping("/admin/product/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model m) {
		Product entity = productDAO.findById(id);
		m.addAttribute("entity", entity);
		m.addAttribute("listCa", categoryDAO.findAll());
		return "admin/product/update";
	}
	
	@RequestMapping("/admin/product/update")
	public String update(@Valid @ModelAttribute("entity") Product entity, Model m,@RequestParam("image_file") MultipartFile file) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			entity.setImage("oppo_A31.png");
		}else {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/products/"+entity.getImage());
			file.transferTo(new File(path));
		}
		boolean isOk = productDAO.update(entity);
		if (isOk) {
			m.addAttribute("message", "Cập nhật dữ liệu thành công!");
		}else {
			m.addAttribute("message", "Cập nhật thất bại");
		}
		return "redirect:/admin/product/index";
	}

}
