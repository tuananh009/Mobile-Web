package bkap.happyshop.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.jsf.FacesContextUtils;

import bkap.happyshop.entity.Category;
import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.OrderDetail;
import bkap.happyshop.entity.Product;
import bkap.happyshop.entity.User;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {
	@Resource(name = "sessionFactory")
	@Autowired
	private SessionFactory factory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.factory = sf;
	}
	
	@Override
	public Order findById(Integer id) {
		Session session = factory.openSession();
		try {
			Order entity = session.find(Order.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		String hql = "FROM Order";
		Session session = factory.openSession();
		try {
			TypedQuery<Order> query = session.createQuery(hql,Order.class);
			List<Order> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
		
		
	}

	@Override
	public Order create(Order entity) {
		Session session = factory.openSession();
		try {
			session.save(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

//	@Override
//	public void update(Order entity) {
//		Session session = factory.openSession();
//		try {
//			session.update(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
//		
//
//	}
//
//	@Override
//	public Order delete(Integer id) {
//		Session session = factory.openSession();
//		try {
//			Order entity = session.find(Order.class, id);
//			session.delete(entity);
//			return entity;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
//		return null;
//	}

	@Override
	public void create(Order order, List<OrderDetail> details) {
		Session session = factory.openSession();
		try {
			session.save(order);
			for (OrderDetail detail : details) {
				session.save(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}		
	}

	@Override
	public List<Order> findByUser(User user) {
		String hql = "FROM Order o WHERE  o.user.id = :uid ORDER BY o.orderDate DESC";
		Session session = factory.openSession();
		try {
			TypedQuery<Order> query = session.createQuery(hql,Order.class);
			query.setParameter("uid", user.getId());
			List<Order> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public List<Product> findItemsByUser(User user) {
		String hql = "SELECT DISTINCT d.product FROM OrderDetail d WHERE d.order.user.id =:uid";
		Session session = factory.openSession();
		try {
			TypedQuery<Product> query = session.createQuery(hql,Product.class);
			query.setParameter("uid", user.getId());
			List<Product> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
		
	}

	@Override
	public boolean update(Order entity) {
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
			Order entity = session.get(Order.class, id);
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

}
