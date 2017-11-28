package com.tunguyen.shop.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.domain.Vocabulary;
import com.tunguyen.shop.service.VocabService;

@Controller
public class NewWordController {
	
	@Autowired
	VocabService vocabService;

	@GetMapping("/newword")
	public String newWord(Model model) {
		Vocabulary vocab = new Vocabulary();
		model.addAttribute("vocab", vocab);
		return "newword";
	}
	
	@PostMapping("/newword")
	public String addNewWord(@Valid @ModelAttribute("vocab") Vocabulary vocab, BindingResult result, Model model, Locale locale,
			HttpSession session){
		if (result.hasErrors()) {
			return "newword";
		}
		vocabService.saveVocab(vocab, session);
		return "redirect:/newword";
	}
}
