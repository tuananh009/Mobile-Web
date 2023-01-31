package bkap.happyshop.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bkap.happyshop.entity.Category;
import bkap.happyshop.entity.Order;
import bkap.happyshop.entity.OrderDetail;

@Transactional
@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {

	@Resource(name = "sessionFactory")
	@Autowired
	private SessionFactory factory;

	public void setSessionFactory(SessionFactory sf) {
		this.factory = sf;
	}

	@Override
	public OrderDetail findById(Integer id) {
		Session session = factory.openSession();
		try {
			OrderDetail entity = session.find(OrderDetail.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public List<OrderDetail> findAll() {
		String hql = "FROM OrderDetail";
		Session session = factory.openSession();
		try {
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			List<OrderDetail> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public OrderDetail create(OrderDetail entity) {
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
	public boolean update(OrderDetail entity) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.update(entity);
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
	public boolean delete(Integer id) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			OrderDetail entity = session.find(OrderDetail.class, id);
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
	public List<OrderDetail> findByOrder(Order order) {
		String hql = "FROM OrderDetail d WHERE d.order.id = :oid";
		Session session = factory.openSession();
		try {
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			query.setParameter("oid", order.getId());
			List<OrderDetail> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}
//	@Override
//	public OrderDetail delete(Integer id) {
//		Session session=factory.getCurrentSession();
//		OrderDetail entity=session.find(OrderDetail.class, id);
//		session.delete(entity);
//		return entity;
//	}

	@Override
	public boolean deleteByOrderId(Integer orderid) {
		String hql = "DELETE FROM OrderDetail d WHERE d.order.id = :oid";
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("oid", orderid);
			query.executeUpdate();
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
