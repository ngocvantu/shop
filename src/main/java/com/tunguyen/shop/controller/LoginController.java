package com.tunguyen.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("user", new User());
		return "login";
	}
		
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model, @RequestParam("chao") String chao) {
		System.out.println(chao);
		if (user.getUsername().equalsIgnoreCase("tunguyen") && user.getPassword().equalsIgnoreCase("1")) {
			User loginUser = userService.login(user);
			System.out.println(loginUser.getEmail());
			return "redirect:/";
		} else {
			// throw new NullPointerException();
			return "login";
		}
	}
}
