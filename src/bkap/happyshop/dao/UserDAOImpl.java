package bkap.happyshop.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bkap.happyshop.entity.Category;
import bkap.happyshop.entity.User;

@Component
@Repository
public class UserDAOImpl implements UserDAO {
	@Resource(name = "sessionFactory")
	@Autowired
	private SessionFactory factory;

	public void setSessionFactory(SessionFactory sf) {
		this.factory = sf;
	}

	@Override
	public User findById(String id) {
		Session session = factory.openSession();
		try {
			User entity = session.find(User.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		String hql = "FROM User";
		Session session = factory.openSession();
		try {
			TypedQuery<User> query = session.createQuery(hql, User.class);
			List<User> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public User create(User entity) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.save(entity);
			session.getTransaction().commit();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
//
//	@Override
//	public void update(User entity) {
//
//		Session session = factory.openSession();
//		session.beginTransaction();
//		try {
//			session.update(entity);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//
//	}

//	@Override
//	public User delete(String id) {
//		Session session = factory.openSession();
//		session.beginTransaction();
//		try {
//			User entity = session.find(User.class, id);
//			session.delete(entity);
//			session.beginTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
//		return null;
//		
//	}

	@Override
	public long getPageCount(int pageSize) {
		String hql = "SELECT count(c) FROM User c";
		Session session = factory.openSession();
		try {
			TypedQuery<Long> query = session.createQuery(hql, Long.class);
			Long rowCount = query.getSingleResult();
			long pageCount = (long) Math.ceil(1.0 * rowCount / pageSize);
			return pageCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (Long) null;

	}

	@Override
	public List<User> getPage(int pageNo, int pageSize) {
		String hql = "FROM User";
		Session session = factory.openSession();
		try {
			TypedQuery<User> query = session.createQuery(hql, User.class);
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
			List<User> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public boolean delete(String id) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			User entity = session.get(User.class, id);
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
	public boolean update(User user) {
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
