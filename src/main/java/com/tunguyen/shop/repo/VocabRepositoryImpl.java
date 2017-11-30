package com.tunguyen.shop.repo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tunguyen.shop.domain.Vocabulary;

@Repository
@Transactional
public class VocabRepositoryImpl implements VocabRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveVocab(Vocabulary vocab) { 
		Session session;
		session = sessionFactory.getCurrentSession();
		session.save(vocab);
	}

	@Override
	public List<Vocabulary> get20Vocab() { 
		Session session;
		session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from vocabulary").setFirstResult(0).setMaxResults(20);

		return query.list();
	}

}
