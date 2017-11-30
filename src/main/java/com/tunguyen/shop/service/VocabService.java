package com.tunguyen.shop.service;

import javax.servlet.http.HttpSession;

import com.tunguyen.shop.domain.Vocabulary;

public interface VocabService {
	public  void saveVocab(Vocabulary vocab, HttpSession session, String image);
}
