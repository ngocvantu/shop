package com.tunguyen.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	ServletContext context;
	
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
			HttpSession session, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getOriginalFilename());
		if (result.hasErrors()) {
			return "newword";
		}
		System.out.println(context.getRealPath(""));
		file.transferTo(new File(context.getRealPath("") + "\\resources\\static\\image\\vocab\\" + file.getOriginalFilename()));
		
		vocabService.saveVocab(vocab, session, file.getOriginalFilename());
		return "redirect:/newword";
	}
}
