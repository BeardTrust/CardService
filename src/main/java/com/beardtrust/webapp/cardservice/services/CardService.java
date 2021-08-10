package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;

public interface CardService {
	
	public List<CardEntity> getAll();
	
	public CardEntity getById(String id);
	
	public void deleteById(String id);
	
	public void save(CardEntity card);

	CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest);

	CardDTO getStatus(String userId, String cardId);
}
