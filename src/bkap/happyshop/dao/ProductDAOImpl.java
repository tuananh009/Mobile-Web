package bkap.happyshop.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import bkap.happyshop.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductDAOImpl implements ProductDAO {

	@Resource(name = "sessionFactory")
	@Autowired
	private SessionFactory factory;

	public void setSessionFactory(SessionFactory sf) {
		this.factory = sf;
	}

	@Override
	public Product findById(Integer id) {
		Session session = factory.openSession();
		try {
			Product entity = session.find(Product.class, id);
			return entity;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		String hql = "From Product";
		Session session = factory.openSession();
		;
		try {
			TypedQuery<Product> query = session.createQuery(hql, Product.class);
			List<Product> list = query.getResultList();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Product create(Product entity) {
		Session session = factory.openSession();
		try {
			session.save(entity);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

//	@Override
//	public void update(Product entity) {
//		Session session = factory.openSession();
//		try {
//			session.update(entity);
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}
//
//	@Override
//	public Product delete(Integer id) {
//		Session session = factory.openSession();
//		try {
//			Product entity = session.find(Product.class, id);
//			session.delete(entity);
//			return entity;
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		return null;
//
//	}
	
	@Override
	public boolean update(Product entity) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.update(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			Product entity = session.get(Product.class, id);
			session.delete(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			session.close();
		}
		return true;
	}


	@Transactional
	@Override
	public List<Product> findByCategoryId(Integer categoryId) {
		String hql = "FROM Product p WHERE p.category.id = :cid";
		Session session = factory.openSession();
		try {
			TypedQuery<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("cid", categoryId);
			List<Product> list = query.getResultList();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		String hql = "FROM Product p WHERE p.name LIKE :kw OR p.category.name LIKE :kw OR p.category.nameVN LIKE :kw";
		Session session = factory.openSession();
		try {
			TypedQuery<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("kw", "%" + keywords + "%");
			List<Product> list = query.getResultList();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> findByIds(String ids) {
		String hql = "FROM Product p WHERE p.id IN (" + ids + ")";
		Session session = factory.openSession();
		try {
			TypedQuery<Product> query = session.createQuery(hql, Product.class);
			List<Product> list = query.getResultList();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> findBySpecial(Integer id) {
		Session session = factory.openSession();
		String hql = "FROM Product p";
		try {
			TypedQuery<Product> query = session.createQuery(hql, Product.class);
			switch (id) {
			// Hang Moi
			case 0:
				hql = "FROM Product p ORDER BY p.productDate DESC";
				break;

			// Ban chay
			case 1:
				hql = "FROM Product p ORDER BY size(p.orderDetails) DESC";
				break;

			// Xem nhieu
			case 2:
				hql = "FROM Product p ORDER BY p.viewCount DESC";
				break;

			// Giam Gia
			case 3:
				hql = "FROM Product p ORDER BY p.discount DESC";
				break;

			// Dac biet
			case 4:
				hql = "FROM Product p WHERE p.special = true ORDER BY p.productDate DESC";
				break;

			}
			query = session.createQuery(hql, Product.class);
			query.setMaxResults(12);
			List<Product> list = query.getResultList();
			session.close();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}


}
