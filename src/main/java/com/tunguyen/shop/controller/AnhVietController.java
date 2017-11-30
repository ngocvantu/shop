package com.tunguyen.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnhVietController {
	
	@GetMapping("anhviet")
	public String anhviet(){
		return "anhviet";
	}
}
