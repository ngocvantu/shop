package com.tunguyen.shop.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String handleLogout(Locale locale, HttpSession sesion) {
		sesion.removeAttribute(LoginController.USER_);
		return "redirect:/";
	}
}
