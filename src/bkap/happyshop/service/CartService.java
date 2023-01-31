package bkap.happyshop.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import bkap.happyshop.dao.ProductDAO;
import bkap.happyshop.entity.Product;

@SessionScope
@Service
public class CartService {
	@Autowired
	ProductDAO productDAO;
	
	Map<Integer, Product> map = new HashMap<Integer, Product>();//hiển thị danh sách giỏ hàng
	public void add(Integer id) {
		Product p = map.get(id);
		if (p == null) {
			p = productDAO.findById(id);
			p.setQuantity(1);
			map.put(id, p);
		}else {
			p.setQuantity(p.getQuantity() + 1);
		}
	}	
	
	//Xóa theo id
	public void remove(Integer id) {
		map.remove(id);
	}
	
	//Cập nhật sản phẩm
	public void update(Integer id, int qty) {
		Product p = map.get(id);
		p.setQuantity(qty);
	}
	
	//Xóa tất cả
	public void clear() {
		map.clear();
	}
	
	//Hiển thị lên danh sách đã chọn
	public Collection<Product> getItems(){
		return map.values();
	}
	
	//Hiển thị số lượng
	public int getCount() {
		Collection<Product> ps = this.getItems();
		int count = 0;
		for (Product p : ps) {
			count += p.getQuantity();
		}
		return count;
	}
		
	//Tính Tổng tiền
	public double getAmount() {
		Collection<Product> ps = this.getItems();
		double amount =0;
		for (Product p : ps) {
			amount += p.getQuantity() * p.getUnitPrice() * (1 - p.getDiscount());
		}
		return amount;
	}
	
}
