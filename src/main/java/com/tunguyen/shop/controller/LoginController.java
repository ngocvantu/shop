package com.tunguyen.shop.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.service.UserService;

@Controller
public class LoginController {
	
	public static final String USER_ = "user";

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(Locale locale, Model model, HttpSession session) {
		if (session.getAttribute(USER_) != null) {
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "login";
	}
		
	@PostMapping("/login")
	public String login(Locale locale, @ModelAttribute("user") User user, Model model, HttpSession session) {
		logger.info("login controller");

		User loginUser = userService.login(user);
		if (loginUser != null) {
			session.setAttribute(USER_, loginUser);
		} else {
			return "login";
		}

		return "redirect:/";
	}
}
