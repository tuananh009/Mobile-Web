package bkap.happyshop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
//import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bkap.happyshop.bean.MailInfo;
import bkap.happyshop.dao.UserDAO;
import bkap.happyshop.entity.User;
import bkap.happyshop.service.CookieService;
import bkap.happyshop.service.MailService;


@Controller
@MultipartConfig
public class AccountController {
	@Autowired
	UserDAO userDAO;

	@Autowired
	HttpSession session;

	@Autowired
	CookieService cookie;

	@Autowired
	ServletContext app;

	@Autowired
	MailService mailService;

	@Autowired
	HttpServletRequest request;

	@GetMapping("/account/login")
	public String login(Model m) {
		Cookie ckid = cookie.read("userid");
		Cookie ckpw = cookie.read("pass");
		if (ckid != null) {
			String uid = ckid.getValue();
			String pwd = ckpw.getValue();

			m.addAttribute("uid", uid);
			m.addAttribute("pwd", pwd);
		}
		return "account/login";
	}

	@PostMapping("/account/login")
	public String login(Model m, @RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {
		User user = userDAO.findById(id);
		if (user == null) {
			m.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu");
		} else if (!pw.equals(user.getPassword())) {
			m.addAttribute("message", "Sai Mật Khẩu");
		} else if (!user.getActivated()) {
			m.addAttribute("message", "Tài khoản chưa được kích hoạt");
		} else if (!user.getAdmin()) {
			m.addAttribute("message", "Bạn Không Có Quyền");
		} else {
			m.addAttribute("message", "Đăng nhập thành công!");
			session.setAttribute("user", user);
			if (rm == true) {
				cookie.create("pass", user.getPassword(), 30);
			} else {
				cookie.delete("userid");
				cookie.delete("pass");
			}

			String backUrl = (String) session.getAttribute("back-url");
			if (backUrl != null) {
				return "redirect:" + backUrl;
			}
			return "redirect:/home";

		}
		return "account/login";
	}

	@RequestMapping("/account/logout")
	public String logout(Model m) {
		session.removeAttribute("user");
		return "redirect:/home";
	}

	@GetMapping("/account/register")
	public String register(Model m) {
		User user = new User();
		m.addAttribute("form", user);

		return "account/register";
	}

	@PostMapping("/account/register")
	public String register(Model m, @Validated @ModelAttribute("form") User user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile files, HttpServletRequest request)
			throws IllegalStateException, IOException, MessagingException, ServletException {
		if (errors.hasErrors()) {
			m.addAttribute("message", "Error");
			return "account/register";
		} else {
			User user2 = userDAO.findById(user.getId());
			if (user2 != null) {
				m.addAttribute("message", "Tên đăng nhập đã được sử dụng");
				return "account/register";
			}
		}
		if (files.isEmpty()) {
		user.setPhoto("user.png");
		} else {
			String dir = app.getRealPath("static/images/customers");
			File f = new File(dir, files.getOriginalFilename());
			files.transferTo(f);
			user.setPhoto(f.getName());
		}
		user.setActivated(false);
		user.setAdmin(false);
		userDAO.create(user);
		m.addAttribute("message", "Đăng ký thành công");

		String from = "happyshop154@gmail.com";
		String to = user.getEmail();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/" + user.getId());
		String body = "Happy shop xin chào! Vui lòng nhấn vào <a href ='" + url
				+ "'>Activate</a> để kích hoạt tài khoản.";
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailService.send(mail);

		return "account/register";
	}
	
	@GetMapping("/account/activate/{id}")
	public String activate(Model m, @PathVariable("id") String id) {
		User user = userDAO.findById(id);
		user.setActivated(true);
		userDAO.update(user);
		return "redirect:/account/login";
	}

	@GetMapping("/account/forgot")
	public String forgot(Model m) {
		return "account/forgot";
	}

	@PostMapping("/account/forgot")
	public String forgot(Model m, @RequestParam("id") String id, @RequestParam("email") String email)
			throws MessagingException {
		User user = userDAO.findById(id);
		if (user == null) {
			m.addAttribute("message", "Tên tài khoản không đúng!");
		} else if (!email.equals(user.getEmail())) {
			m.addAttribute("message", "Email Không đúng!");
		} else {
			String from = "happyshop154@gmail.com";
			String to = user.getEmail();
			String subject = "Quên mật khẩu";
			String body = "Happy shop xin chào! Mật khẩu của bạn là: " + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailService.send(mail);
			m.addAttribute("message", "Mật khẩu đã được gửi đến mail của bạn!");
		}
		return "account/forgot";
	}

	@GetMapping("/account/change")
	public String change(Model m) {
		User user = (User) session.getAttribute("user");
		m.addAttribute("form", user);

		return "account/change";
	}

	@PostMapping("/account/change")
	public String change(Model m, @ModelAttribute("form") User users, @RequestParam("id") String id,
			@RequestParam("pw") String pw, @RequestParam("pw1") String pw1, @RequestParam("pw2") String pw2) {
		if (!pw1.equals(pw2)) {
			m.addAttribute("message", "Xác nhận mật khẩu không trùng khớp");
		} else {
			User user = userDAO.findById(id);
			if (user == null) {
				m.addAttribute("message", "Sai tài khoản");
			} else if (!pw.equals(user.getPassword())) {
				m.addAttribute("message", "Mật khẩu hiện tại không đúng");
			} else {
				user.setPassword(pw1);
				userDAO.update(user);

				m.addAttribute("message", "Thay đổi mật khẩu thành công");
			}
		}
		return "account/change";
	}

	@GetMapping("/account/edit")
	public String edit(Model m) {
		User user = (User) session.getAttribute("user");
		m.addAttribute("form", user);

		return "account/edit";
	}

	@PostMapping("/account/edit")
	public String edit(Model m, @ModelAttribute("form") User user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			m.addAttribute("message", "Vui lòng sửa các lỗi sau");
			return "account/edit";
		}
		if (!file.isEmpty()) {
			String dir = app.getRealPath("static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		userDAO.update(user);
		session.setAttribute("user", user);
		m.addAttribute("message", "Cập nhật tài khoản thành công!");

		return "account/edit";
	}
}
