package com.beardtrust.webapp.cardservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.repos.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	private CardRepository cardRepo;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepo) {
		this.cardRepo = cardRepo;
	}

	@Override
	@Transactional
	public List<CardEntity> getAll() {
		
		List<CardEntity> list = cardRepo.findAll();
		
		return list;
	}

	@Override
	@Transactional
	public CardEntity getById(String id) {
  
		Optional<CardEntity> result = cardRepo.findById(id);
		
		CardEntity card = null;
		
		if(result.isPresent()) {
			card = result.get();
		}
		else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
		
		return card;
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		
		Optional<CardEntity> result = cardRepo.findById(id);
		
		if(result.isPresent()) {
			cardRepo.deleteById(id);
		}
		else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
	}

	@Override
	@Transactional
	public void save(CardEntity card) {
		cardRepo.save(card);
		}

}
