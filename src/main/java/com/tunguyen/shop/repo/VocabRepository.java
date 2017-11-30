package com.tunguyen.shop.repo;

import java.util.List;

import com.tunguyen.shop.domain.Vocabulary;

public interface VocabRepository {
	public void saveVocab(Vocabulary vocab);
	public List<Vocabulary> get20Vocab();
}
