package com.tunguyen.shop.controller;

import java.util.Locale;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class IndexController {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@GetMapping("/")
	public String index(Locale locale, Model model) {
		String sql = "CREATE TABLE IF NOT EXISTS `hello` (`id` int(11) NOT NULL,`name` varchar(20) NOT NULL)";
		 
		Session session;
		session = sessionFactory.getCurrentSession();
		session.createSQLQuery(sql).executeUpdate();
		
		
		
		return "index";
	}
}
