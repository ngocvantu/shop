package com.tunguyen.shop.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunguyen.shop.controller.LoginController;
import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.domain.Vocabulary;
import com.tunguyen.shop.repo.VocabRepository;

@Service
public class VocabServiceImpl implements VocabService{

	@Autowired
	VocabRepository vocabRepository;
	
	@Override
	public void saveVocab(Vocabulary vocab, HttpSession session, String image) { 
		vocab.setNgaynhap(new Date());
		vocab.setDathuoc(false);
		vocab.setImage(image);
		vocab.setUserid(((User)session.getAttribute(LoginController.USER_)).getId());
		vocabRepository.saveVocab(vocab);
	}

}
