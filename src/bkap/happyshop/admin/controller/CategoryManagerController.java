package bkap.happyshop.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bkap.happyshop.dao.CategoryDAO;
import bkap.happyshop.dao.CategoryDAOImpl;
import bkap.happyshop.entity.Category;

@Transactional
@Controller
public class CategoryManagerController {
	@Resource(name = "categoryDAO")
	private CategoryDAO categoryDAO;

//	@Autowired
//	private CategoryDAO categoryDAO;

	@RequestMapping("/admin/category/index")
	public String index(Model m) {
		Category entityCa = new Category();
		List<Category> list = categoryDAO.findAll();
		m.addAttribute("entityCa", entityCa);
		m.addAttribute("list", list);
		return "admin/category/index";
	}

//	@RequestMapping("/admin/category/edit/{id}")
//	public String edit(Model m, @PathVariable("id") Integer id) {
//		Category entity = categoryDAO.findById(id);
//		m.addAttribute("entity", entity);
//		m.addAttribute("list", categoryDAO.findAll());
//		return "admin/category/index";
//	}

//	@RequestMapping(value = "/admin/category/update")
//	public String update(RedirectAttributes m, @ModelAttribute("entity") Category entity) {
//		categoryDAO.update(entity);
//		m.addAttribute("message", "Cập nhật thành công");
//		return "redirect:/admin/category/edit/"+entity.getId();
//	}

	@RequestMapping("/admin/category/init-update/{id}")
	public String initUpdate(@PathVariable(name = "id") Integer id, Model m) {
		Category category = categoryDAO.findById(id);
		m.addAttribute("category", category);
		return "admin/category/update";
	}

	@RequestMapping("admin/category/update")
	public String update(@Valid @ModelAttribute("category") Category category, Model m) {
//		Category entity = new Category();
//		m.addAttribute("entity", entity);
//		m.addAttribute("list", categoryDAO.findAll());
		boolean isOk = categoryDAO.update(category);
		if (isOk) {
			m.addAttribute("message", "Cập nhật dữ liệu thành công!");
		} else {
			m.addAttribute("message", "Thất bại!");

		}
		return "redirect:/admin/category/index";
	}

	@RequestMapping("/admin/category/create")
	public String create(RedirectAttributes model, @ModelAttribute("entity") Category entity) {
		categoryDAO.create(entity);
		model.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/category/index";
	}

//	@RequestMapping("/admin/category/update")
//	public String update(Model m, @ModelAttribute("entity") Category entity , BindingResult result) {
//		m.addAttribute("entity", entity);
//		if (!result.hasErrors()) {
//			boolean isOk = categoryDAO.update(entity);
//			if (isOk) {
//				m.addAttribute("message", "Cập nhật dữ liệu thành công!");
//			}else {
//				m.addAttribute("message", "Thất bại");
//			}
//		}else {
//			m.addAttribute("message", "Có lỗi");
//		}
//	
//		return "redirect:/admin/category/index";
//	}

	@RequestMapping(value = { "/admin/category/delete", "/admin/category/delete/{id}" })
	public String delete(Model m, @PathVariable(value = "id", required = false) Integer id) {
		boolean isOk = categoryDAO.delete(id);
		if (isOk) {
			m.addAttribute("message", "Xóa thành công");
		} else
			m.addAttribute("message", "Xóa Thất bại");
		List<Category> list = categoryDAO.findAll();
		m.addAttribute("list", list);
		return "redirect:/admin/category/index";
	}

}
