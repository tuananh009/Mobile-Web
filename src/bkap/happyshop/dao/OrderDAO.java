package bkap.happyshop.dao;

import java.util.List;

import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.OrderDetail;
import bkap.happyshop.entity.Product;
import bkap.happyshop.entity.User;

public interface OrderDAO {
	Order findById(Integer id);
	
	List<Order> findAll();
	
	Order create(Order entity);
	
	public boolean update(Order entity);
	
	public boolean delete(Integer id);
	
	void create(Order order, List<OrderDetail> details);
	
	List<Order> findByUser(User user);
	
	List<Product> findItemsByUser(User user);
}
