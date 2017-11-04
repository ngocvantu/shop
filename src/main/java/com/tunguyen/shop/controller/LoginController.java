package com.tunguyen.shop.controller;

import javax.print.attribute.standard.Media;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import com.tunguyen.shop.domain.User;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
	}
		
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) { 
		
		if (user.getUsername().equalsIgnoreCase("tunguyen") && user.getPassword().equalsIgnoreCase("1")) {
			return "index";
		} else {
//			throw new NullPointerException();
			return "redirect:/login";
		}
	}
}
