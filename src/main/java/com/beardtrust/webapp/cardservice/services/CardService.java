package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;

public interface CardService {
	
	public List<CardEntity> getAll();
	
	public CardEntity getById(String id);
	
	public void deactivateById(String id);
	
	public void update(CardUpdateModel cardUpdateModel);
	
	CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest);
	
	public CardSignUpResponseModel registerCard(String userId, CardRegistrationModel cardRegistration);

}
