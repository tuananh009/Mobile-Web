package bkap.happyshop.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bkap.happyshop.dao.UserDAO;
import bkap.happyshop.entity.User;

@Controller
public class UserManagerController {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	ServletContext app;
	
	@RequestMapping("/admin/customer/index")
	public String index(Model m) {
		User entity = new User();
		m.addAttribute("entity", entity);
		m.addAttribute("list", userDAO.findAll());
		return "admin/customer/index";
	}
	
//	@RequestMapping("/admin/customer/edit/{id}")
//	public String edit(Model m, @PathVariable("id") String id) {
//		User entity = userDAO.findById(id);
//		m.addAttribute("entity", entity);
//		m.addAttribute("list", userDAO.findAll());
//		return "admin/customer/index";
//		
//	}
	
	@RequestMapping("/admin/customer/create")
	public String create(RedirectAttributes m, @Validated @ModelAttribute("entity") User entity,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException,IOException{
		User user2 = userDAO.findById(entity.getId());
		if (user2 != null) {
			m.addAttribute("message ", "Tên đăng nhập đã được sử dụng");
			return "redirect:/admin/customer/index";
		}
		if (file.isEmpty()) {
			entity.setPhoto("user.png");
					}
		else {
			entity.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customers" + entity.getPhoto());
			file.transferTo(new File(path));
		}
		userDAO.create(entity);
		m.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/init-update/{id}")
	public String initUpdate(@PathVariable("id") String id,Model m) {
		User user = userDAO.findById(id);
		m.addAttribute("user", user);
		return "admin/customer/update";
	}
	
	@RequestMapping("/admin/customer/update")
	public String update(@Valid @ModelAttribute("user") User user, Model m,@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		if (file.isEmpty()) {
			user.setPhoto("user.png");
					}
		else {
			user.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customers" + user.getPhoto());
			file.transferTo(new File(path));
		}
		boolean isOk = userDAO.update(user);
		if (isOk) {
			m.addAttribute("message", "Cập nhật dữ liệu thành công!");
		}else {
			m.addAttribute("message", "Thất bại");
		}
		return "redirect:/admin/customer/index";
	}
	
//	@RequestMapping("/admin/customer/update")
//	public String update(RedirectAttributes m, @ModelAttribute("entity") User entity,
//			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException,IOException{
//		if (!file.isEmpty()) {
//			entity.setPhoto(file.getOriginalFilename());
//			String path = app.getRealPath("/static/images/customers/" + entity.getPhoto());
//			file.transferTo(new File(path));
//		}
//		userDAO.create(entity);
//		m.addAttribute("message", "Cập nhật thành công!");
//		return "redirect:/admin/customer/edit/" + entity.getId();
//	}
//	
	@RequestMapping(value = {"/admin/customer/delete","/admin/customer/delete/{id}"})
	public String delete(Model m, @PathVariable(value = "id",required = false) String id) {
			boolean isOk = userDAO.delete(id);
			if (isOk) {
				m.addAttribute("message", "Xóa thành công");
			}else {
				m.addAttribute("message", "Thất bại");
			}
		return "redirect:/admin/customer/index";
	}
	
	int pageSize = 8;
	@ResponseBody
	@RequestMapping("/pager/customer/page-count")
	public long pageConut() {
		return userDAO.getPageCount(pageSize);
	}
	
	@ResponseBody
	@RequestMapping("/pager/customer/page/{pageNo}")
	public List<User> getPage(@PathVariable("pageNo") int pageNo){
		List<User> list = userDAO.getPage(pageNo, pageSize);
		return list;
	}
}
