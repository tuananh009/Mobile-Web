package bkap.happyshop.dao;

import java.util.List;

import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.OrderDetail;

public interface OrderDetailDAO {
	OrderDetail findById(Integer id);
	
	List<OrderDetail> findAll();
	
	OrderDetail create(OrderDetail entity);
	
	public boolean update(OrderDetail entity);
	
	public boolean delete(Integer id);
	
	public boolean deleteByOrderId(Integer orderid);
	
	List<OrderDetail> findByOrder(Order order);
	
}
