package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;

@Service
public class CardTypeServiceImpl implements CardTypeService {
	
	CardTypeRepository cardTypeRepo;
	
	@Autowired
	public CardTypeServiceImpl(CardTypeRepository cardTypeRepo) {
		this.cardTypeRepo = cardTypeRepo;
	}
	
	@Override
	@Transactional
	public void save(CardTypeEntity cardType) {
		cardTypeRepo.save(cardType);
		}
	
	@Override
	@Transactional
	public List<CardTypeEntity> getAll(){
		return cardTypeRepo.findAll();
	}

}
