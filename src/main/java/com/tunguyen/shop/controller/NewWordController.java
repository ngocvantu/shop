package com.tunguyen.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tunguyen.shop.domain.Vocabulary;

@Controller
public class NewWordController {

	@GetMapping("/newword")
	public String newWord(Model model) {
		Vocabulary vocab = new Vocabulary();
		model.addAttribute("vocab", vocab);
		return "newword";
	}
}
