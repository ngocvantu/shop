package com.tunguyen.shop.controller;

import java.util.Locale;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Controller
// @Transactional
public class IndexController implements ApplicationListener<ApplicationEvent> {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Locale locale, Model model) {
		// String sql = "CREATE TABLE IF NOT EXISTS `hello` (`id` int(11) NOT
		// NULL,`name` varchar(20) NOT NULL)";
		//
		// Session session;
		// session = sessionFactory.getCurrentSession();
		// session.createSQLQuery(sql).executeUpdate();
		
		return "index";
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		ServletRequestHandledEvent evt = (ServletRequestHandledEvent) event;
		if (evt.getRequestUrl().equals("/shop/") || evt.getRequestUrl().contains("/shop/;jsessionid")) {
			logger.info(evt.getProcessingTimeMillis() + " milisec - index");
		}
	}
}
