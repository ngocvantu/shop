package com.tunguyen.shop.repo;

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
	public User findByUsername(String username) {
		Session session;
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from user where username = :name ");
		query.setParameter("name", username);
		User list = (User) query.uniqueResult();
		return list;
	}
}
