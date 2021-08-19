package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;

public interface CardTypeService {
	
	public void save(CardTypeEntity cardType);
	
	public List<CardTypeEntity> getAll();
}
