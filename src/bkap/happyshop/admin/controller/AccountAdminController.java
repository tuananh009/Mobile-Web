package bkap.happyshop.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.naming.Binding;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bkap.happyshop.dao.UserDAO;
import bkap.happyshop.entity.User;
import bkap.happyshop.service.CookieService;

@Controller
public class AccountAdminController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	HttpSession session;
	
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/admin/login")
	public String login(Model m) {
		Cookie ckid = cookieService.read("userid");
		Cookie ckpw = cookieService.read("pass");
		if (ckid != null) {
			String uid = ckid.getValue();
			String pwd = ckpw.getValue();
			
			m.addAttribute("uid", uid);
			m.addAttribute("pwd", pwd);
		}
		return "admin/login/login";
	}
	
	@PostMapping("/admin/login")
	public String login(Model m,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {
		User user = userDAO.findById(id);
		
		if (user == null) {
			m.addAttribute("message", "Sai tài khoản hoặc mật khẩu");
		}else if(!pw.equals(user.getPassword())){
			m.addAttribute("message", "Sai mập khẩu ");
		}else if(!user.getActivated()){
			m.addAttribute("message", "Tài khoản chưa được kích hoạt");
		}else if(!user.getAdmin()){
			m.addAttribute("message", "Không có quyền");
		}else {
			//Thành công
			m.addAttribute("message", "Login Successfully!");
			session.setAttribute("user", user);
			
			//Ghi nho tai khoan
			if (rm==true) {
				cookieService.create("userid", user.getId(), 30);
				cookieService.create("pass", user.getPassword(), 30);
			}else {
				cookieService.delete("userid");
				cookieService.delete("pass");
			}
			
			//Quay lai trang bao ve
			String backUrl = (String) session.getAttribute("back-admin-url");
			if (backUrl !=null) {
				return "redirect:" + backUrl;
			}
			return "redirect:/admin/home/index";
		}
		return "admin/login/login";
	}
	
	@RequestMapping("/admin/logout")
	public String logout(Model m) {
		session.removeAttribute("user");
		return "redirect:/admin/login";
	}
	
	@GetMapping("/admin/account/activate/{id}")
	public String activate(Model m, @PathVariable("id") String id) {
		User user = userDAO.findById(id);
		user.setActivated(true);
		userDAO.update(user);
		return "redirect:/admin/account/login";
	}
	
	@GetMapping("/admin/profile")
	public String edit(Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("form", user);
		
		return "admin/account/edit";
	}
	
	@PostMapping("/admin/profile")
	public String edit(Model m, @ModelAttribute("form") User user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException, MessagingException{
		if (errors.hasErrors()) {
			m.addAttribute("messeage", "Vui lòng sửa lỗi");
			return "admin/account/edit";
		}
		if (!file.isEmpty()) {
			String dir = servletContext.getRealPath("static/images/admin");
			File f = new File(dir,file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		userDAO.update(user);
		session.setAttribute("user", user);
		
		m.addAttribute("message", "Cập nhật thành công!");
		
		return "admin/accoune/eidt";
	}
	@GetMapping("/admin/change")
	public String change(Model m) {
		User user = (User) session.getAttribute("user");
		m.addAttribute("form", user);
		return "admin/account/change";
	}
	
	@PostMapping("/admin/change")
	public String change(Model m, @ModelAttribute("form") User users,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1,
			@RequestParam("pw2") String pw2) {
		if (!pw1.equals(pw2)) {
			m.addAttribute("message", "Mật khẩu không trùng khớp");
		}else {
			User user = userDAO.findById(id);
			if (user == null) {
				m.addAttribute("message", "Sai tài khoản");
			}else if(!pw.equals(user.getPassword())) {
				m.addAttribute("message", "Mật khẩu hiện tại không đúng");
			}else {
				user.setPassword(pw1);
				userDAO.update(user);
			}
		}
		return "admin/account/change";
	}
}
