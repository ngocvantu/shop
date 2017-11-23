package com.tunguyen.shop.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tunguyen.shop.domain.User;

@Controller
public class SocialLoginController {
	
	@Autowired
	LoginController lgolinController;
	
	@RequestMapping(value = "/sociallogin", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String email, HttpSession session, Locale locale){
		JSONObject jsonObject = new JSONObject(email);
		String useremail = jsonObject.getString("email");
		System.out.println(useremail);
		
		User user = new User();
		user.setEmail(useremail);
		user.setUsername("tunguyen4078");
		user.setId(12);
		user.setPassword("asdf");
		
		session.setAttribute(LoginController.USER_, user);
		User u = (User) session.getAttribute(LoginController.USER_);
		System.out.println("user sesssion " + u.getUsername());
		
		return "{\"chao\":\"none\"}";
	}
}
