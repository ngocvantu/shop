package com.tunguyen.shop;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Service
@Transactional
public class IndexController {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@GetMapping("/")
	public String index(Model model){
		model.addAttribute("currentMenu", "anhviet");
		
		Session session;
		session = sessionFactory.getCurrentSession();
		
		return "index";
	}
}
