package com.tunguyen.shop.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String addNewWord(@Valid @ModelAttribute("vocab") Vocabulary vocab, BindingResult result, Model model, Locale locale,
			HttpSession session){
		System.out.println(vocab.getTuvung());
		if (result.hasErrors()) {
			return "newword";
		}
		return "redirect:/newword";
	}
}
