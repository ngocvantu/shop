package com.tunguyen.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunguyen.shop.domain.Vocabulary;

@Controller
public class NewWordController {

	@GetMapping("/newword")
	public String newWord(Model model) {
		Vocabulary vocab = new Vocabulary();
		model.addAttribute("vocab", vocab);
		return "newword";
	}
	
	@PostMapping("/newword")
	public String addNewWord(@ModelAttribute Vocabulary vocab){
		System.out.println(vocab.getTuvung());
		return "redirect:/newword";
	}
}
