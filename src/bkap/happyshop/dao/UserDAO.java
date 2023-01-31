package bkap.happyshop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bkap.happyshop.entity.User;

@Repository
public interface UserDAO {
	User findById(String id);
	
	List<User> findAll();
	
	User create(User entity);
	
	public boolean update(User user);
	
	public boolean delete(String id);
	
	long getPageCount(int pageSize);
	
	List<User> getPage(int pageNo, int pageSize);
}
