package bkap.happyshop.dao;

import java.util.List;

import bkap.happyshop.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProductDAO {
	Product findById(Integer id);
	
	List<Product> findAll();
	
	Product create(Product entity);
	
	public boolean update(Product entity);
	
	public boolean delete(Integer id);
	
	List<Product> findByCategoryId(Integer categoryId);
	
	List<Product> findByKeywords(String keywords);
	
	List<Product> findByIds(String ids);
	
	List<Product> findBySpecial(Integer id);
	
}
