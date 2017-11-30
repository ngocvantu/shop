package com.tunguyen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tunguyen.shop.domain.Vocabulary;
import com.tunguyen.shop.repo.VocabRepository;
import com.tunguyen.shop.service.VocabService;

@Controller
public class AnhVietController {
	
	@Autowired
	VocabRepository vocabRepo;
	
	@GetMapping("anhviet")
	public String anhviet(Model model, HttpSession sesion){
		List<Vocabulary> listVocab = vocabRepo.get20Vocab();
		for (Vocabulary vocabulary : listVocab) {
			if (vocabulary.getVidu1().length() > 89) {
				vocabulary.setVidu1(vocabulary.getVidu1().substring(0, 88));
			}
			if (vocabulary.getVidu2().length() > 91) {
				vocabulary.setVidu2(vocabulary.getVidu2().substring(0, 90));
			}
		}
		
		model.addAttribute("listVocab", listVocab);
		return "anhviet";
	}
}
