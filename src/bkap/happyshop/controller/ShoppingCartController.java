package bkap.happyshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bkap.happyshop.service.CartService;

@Controller
public class ShoppingCartController {
	@Autowired
	CartService cartService;
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}")
	public Object[] update(@PathVariable("id") Integer id, @PathVariable("qty") Integer qty) {
		cartService.update(id, qty);
		Object[] info = {cartService.getCount(),cartService.getAmount()};
		return info;
	}
	
	@ResponseBody
	@RequestMapping("/cart/add/{id}")
	public Object[] add(@PathVariable("id") Integer id) {
		cartService.add(id);
		Object[] info = {cartService.getCount(), cartService.getAmount()};
		return info;
	}
	
	@ResponseBody
	@RequestMapping("/cart/remove/{id}")
	public Object[] remove(@PathVariable("id") Integer id) {
		cartService.remove(id);
		Object[] info = {cartService.getCount(),cartService.getAmount()};
		return info;
	}
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
//		List listProduct = cartService.
		return "cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/clear")
	public void clear() {
		cartService.clear();
	}
}
