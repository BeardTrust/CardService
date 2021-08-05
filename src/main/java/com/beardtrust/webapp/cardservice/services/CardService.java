package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import com.beardtrust.webapp.cardservice.entities.CardEntity;

public interface CardService {
	
	public List<CardEntity> getAll();
	
	public CardEntity getById(String id);
	
	public void deleteById(String id);
	
	public void save(CardEntity card);

}
