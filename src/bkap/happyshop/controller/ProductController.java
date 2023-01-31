package bkap.happyshop.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import bkap.happyshop.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bkap.happyshop.bean.MailInfo;
import bkap.happyshop.dao.ProductDAO;
import bkap.happyshop.entity.Product;
import bkap.happyshop.service.CookieService;
import bkap.happyshop.service.MailService;

@Controller
public class ProductController {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	CookieService cookie;
	
	@Autowired
	MailService mail;

	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory(Model m,@PathVariable("cid") Integer categoryId) {
		List<Product> list = productDAO.findByCategoryId(categoryId);
		m.addAttribute("list", list);
		return "product/list";

	}

	@RequestMapping("/product/list-by-categorys/{cid}")
	public String listByCategorys(Model m, @PathVariable("cid") Integer categoryId) {
		List<Product> list = productDAO.findByCategoryId(categoryId);
		m.addAttribute("list", list);
		return "product/list_copy";
	}

	@RequestMapping("/product/list-by-keywords")
	public String listByKeywords(Model m, @RequestParam("keywords") String keywords) {
		List<Product> list = productDAO.findByKeywords(keywords);
		m.addAttribute("list5", list);
		return "product/list";
	}

	@RequestMapping("/product/list-by-special/{id}")
	public String listBySpecial(Model m, @PathVariable("id") Integer id) {
		List<Product> list = productDAO.findBySpecial(id);
		m.addAttribute("list", list);
		return "product/list_special_full";
	}

	@RequestMapping("/product/list-by-new/{id}")
	public String listByNews(Model m, @PathVariable("id") Integer id) {
		List<Product> list = productDAO.findBySpecial(id);
		m.addAttribute("list1", list);
		return "product/list-by-new_full";
	}
	@RequestMapping("/product/detail/{id}")
	public String detail(Model m, @PathVariable("id") Integer id) {
		Product prod = productDAO.findById(id);
		m.addAttribute("prod", prod);

		//Tăng số lần xem sản phẩm
		prod.setViewCount(prod.getViewCount() + 1);
		productDAO.update(prod);

		//Hàng cùng loại
		List<Product> list = productDAO.findByCategoryId(prod.getCategory().getId());
		m.addAttribute("list", list);

		//Hàng yêu thích
		Cookie favo = cookie.read("favo");
		if (favo != null) {
			String ids = favo.getValue();
			List<Product> favo_list = productDAO.findByIds(ids);
			m.addAttribute("favo", favo_list);
		}
		
//		//Hàng đã xem
//		Cookie viewed = cookie.read("viewed");
//		String value = id.toString();
//		if ( viewed != null) {
//			value = viewed.getValue();
//			value += "," + id.toString();
//		}
//		cookie.create("viewed", value, 10);
//		List<Product> viewed_list = productDAO.findByIds(value);
//		m.addAttribute("viewed", viewed_list);
		return "product/detail";
	}

	@ResponseBody
	@RequestMapping("product/add-to-favo/{id}")
	public String addToFavorite(Model m, @PathVariable("id") Integer id) {
		Cookie favo = cookie.read("favo");
		String value = id.toString();
		if (favo != null) {
			value = favo.getValue();
			if (!value.contains(id.toString())) {
				value +="," + id.toString();
			}else
				return "false";
		}
		cookie.create("favo", value , 30);
		return "true";
	}
	@ResponseBody
	@RequestMapping("/product/send-to-friend")
	public String sendtoFriends(Model model, MailInfo info, HttpServletRequest req) {
		info.setSubject("Thông tin hàng hóa");
		try {
			String id = req.getParameter("id");
			String link = req.getRequestURL().toString().replace("send-to-friend", "detail/"+id);
			info.setBody(info.getBody()+"<hr><a href='"+link+"'>Xem chi tiết...</a>");
			mail.send(info);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return null;
	}
	

	@RequestMapping("/product/favo")
	public String favo(Model m) {
		//Hang Yeu Thich
		Cookie favo = cookie.read("favo");
		if (favo != null) {
			String ids = favo.getValue();
			List<Product> favo_list = productDAO.findByIds(ids);
			m.addAttribute("favo", favo_list);

		}
		return "product/favo";
	}
}

