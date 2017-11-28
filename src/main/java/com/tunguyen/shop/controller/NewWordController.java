package com.tunguyen.shop.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			HttpSession session, @RequestParam("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		if (result.hasErrors()) {
			return "newword";
		}
		vocabService.saveVocab(vocab, session);
		return "redirect:/newword";
	}
}
