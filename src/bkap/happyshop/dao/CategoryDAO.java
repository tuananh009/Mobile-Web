package bkap.happyshop.dao;

import java.util.List;

import bkap.happyshop.entity.Category;

public interface CategoryDAO {
	
	Category findById(Integer id);
	
	List<Category> findAll();
	
	Category create(Category entity);
	
//	void update(Category entity);
	public boolean update(Category category);
	
	public boolean delete(Integer id);
}
