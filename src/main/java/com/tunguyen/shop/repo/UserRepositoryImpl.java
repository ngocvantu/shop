package com.tunguyen.shop.repo;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tunguyen.shop.domain.User;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User findByUsername(String username, String pass) {
		Session session;
		session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from user where username = :name and password = :pass");
		
		query.setParameter("name", username);
		query.setParameter("pass", DigestUtils.md5Hex(pass));
		User list = (User) query.uniqueResult();
		return list;
	}

	@Override
	public User findUserFaceByEmail(String email) {
		Session session;
		session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from user where email = :mail and userType = 'face'");

		query.setParameter("mail", email);
		User list = (User) query.uniqueResult();
		return list;
	}

	@Override
	public User findUserGoByEmail(String email) {
		Session session;
		session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from user where email = :mail and userType = 'go'");

		query.setParameter("mail", email);
		User list = (User) query.uniqueResult();
		return list;
	}

	public boolean checkEmailExist(String email) {
		Session session;
		session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from user where email = :mail");

		query.setParameter("mail", email);
		User list = (User) query.uniqueResult();
		if (list != null) {
			return true;
		}
		return false;
	}

	@Override
	public void save(User user) {
		Session session;
		session = sessionFactory.getCurrentSession();
		session.save(user);
	}
}
