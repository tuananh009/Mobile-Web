package bkap.happyshop.dao;

import java.util.List;

import javax.annotation.Resource;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bkap.happyshop.entity.Category;

@Repository
@Service("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	@Resource(name = "sessionFactory")
	@Autowired
	private SessionFactory factory;
	
	public SessionFactory getSessionFactory() {
		return factory;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.factory = sf;
	}

	@Override
	public Category findById(Integer id) {
		Session session = factory.openSession();
		session.beginTransaction();
//		try {
//			Category entity = session.get(Category.class, id);
//			return entity;
//		} catch (Exception e) {
//			e.printStackTrace();Ã
//		} finally {
//			session.close();
//		}
		Category category = session.get(Category.class, id);
		session.close();
		return category;

	}

	@Override
	public List<Category> findAll() {
		String hql = "FROM Category";
		Session session = factory.openSession();
		try {
			TypedQuery<Category> query = session.createQuery(hql, Category.class);
			List<Category> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Category create(Category entity) {
		Session session = factory.openSession();
		try {
			session.save(entity);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		Session session = factory.openSession();
		session.beginTransaction();

		try {
			Category entity = session.get(Category.class, id);
			session.delete(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean update(Category category) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.update(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		session.close();
		return true;
	}
//	@Override
//	public void update(Category entity) {
//		Session session = factory.openSession() ;
//		session.beginTransaction();
//		try {
//			session.update(entity);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
//
//	}

}
