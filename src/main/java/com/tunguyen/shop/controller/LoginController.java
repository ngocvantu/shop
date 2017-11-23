package com.tunguyen.shop.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.exception.AuthenticationException;
import com.tunguyen.shop.service.UserService;

@Controller
public class LoginController implements ApplicationListener<ApplicationEvent> {
	
	public static final String USER_ = "user";

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(Locale locale, Model model, HttpSession session) {
		if (session.getAttribute(USER_) != null) {
			System.out.println("session not null");
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "login";
	}
		
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Locale locale,
			HttpSession session) throws AuthenticationException {
		logger.info("login controller");
		if (result.hasErrors()) {
			return "login";
		}
		User loginUser = userService.login(user);
		if (loginUser != null) {
			session.setAttribute(USER_, loginUser);
		} else {
			model.addAttribute("wrongcridentical", true);
			return "login";
		}
		return "redirect:/";
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		ServletRequestHandledEvent evt = (ServletRequestHandledEvent) event;
		if (evt.getRequestUrl().contains("login")) {
			logger.info(evt.getProcessingTimeMillis() + " milisec - login");
		}
	}
	
}
