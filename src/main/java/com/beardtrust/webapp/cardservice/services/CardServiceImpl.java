package com.beardtrust.webapp.cardservice.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

	private CardRepository cardRepo;
	private final CardTypeRepository cardTypeRepo;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepo, CardTypeRepository cardTypeRepo) {
		this.cardTypeRepo = cardTypeRepo;
		this.cardRepo = cardRepo;
	}

	@Override
	@Transactional
	public List<CardEntity> getAll() {
		
		List<CardEntity> list = cardRepo.findAllActive();
		log.info("All cards have been retrieved");
		
		return list;
	}

	@Override
	@Transactional
	public CardEntity getById(String id) {
  
		Optional<CardEntity> result = cardRepo.findById(id);
		
		CardEntity card = null;
		
		if(result.isPresent()) {
			card = result.get();
			log.info("Card id - " + id + " has been retrieved");
		}
		else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
		
		return card;
	}

	@Override
	@Transactional
	public void deactivateById(String id) {
		
		Optional<CardEntity> result = cardRepo.findById(id);
		
		if(result.isPresent()) {
			cardRepo.deactivateById(id);
			log.info("Card id - " + id + " has been deactivated");
		}
		else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
	}
	
	@Override
	@Transactional
	public void update(CardUpdateModel cardUpdateModel) {
		System.out.println("updateServiceImpl");
		System.out.println(cardUpdateModel);
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardUpdateModel.getCardType());
		System.out.println(cardType.get());
		Optional<CardEntity> card = cardRepo.findById(cardUpdateModel.getCardId());
		card.get().setCardId(cardUpdateModel.getCardId());
		card.get().setUserId(cardUpdateModel.getUserId());
		card.get().setCardType(cardType.get());
		card.get().setBalance(cardUpdateModel.getBalance());
		card.get().setCardNumber(cardUpdateModel.getCardNumber());
		card.get().setInterestRate(cardUpdateModel.getInterestRate());
		card.get().setCreateDate(cardUpdateModel.getCreateDate());
		card.get().setNickname(cardUpdateModel.getNickname());
		card.get().setBillCycleLength(cardUpdateModel.getBillCycleLength());
		card.get().setExpireDate(cardUpdateModel.getExpireDate());
		System.out.println(card.toString());
		try {
		CardEntity result = cardRepo.save(card.get());
		System.out.println(result);
		System.out.println("updating in card repo");
		//log.info("Card id - " + card.getCardId() + " has been saved");
		}
		catch(Exception e) {
			System.out.println("Could not save card");
		}
	}
	
	@Override
	@Transactional
	public CardSignUpResponseModel registerCard(String userId, CardRegistrationModel cardRegistration) {
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardRegistration.getCardType());
		CardEntity card = new CardEntity();
		
		if(cardType.isPresent()) {
			card.setBalance(0.00);
			card.setCardType(cardType.get());
			card.setInterestRate(cardType.get().getBaseInterestRate() + cardRegistration.getInterestRate());
			card.setUserId(userId);
			card.setCardNumber(generateCardNumber());
			card.setCardId(UUID.randomUUID().toString());
			card.setActiveStatus(true);
			card.setBillCycleLength(cardRegistration.getBillCycleLength());
			card.setCreateDate(LocalDate.now());
			card.setExpireDate(card.getCreateDate().plusYears(3));
			card.setNickname(cardRegistration.getNickname());
			card = cardRepo.save(card);
		}
		
		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setCardId(card.getCardId());
		return response;
	}
	
	
	@Override
	@Transactional
	public CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest){
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(signUpRequest.getCardType());
		CardEntity card = new CardEntity();

		if(cardType.isPresent()){
			card.setBalance(0.00);
			card.setCardType(cardType.get());
			card.setInterestRate(cardType.get().getBaseInterestRate());
			card.setUserId(userId);
			card.setCardNumber(generateCardNumber());
			card.setCardId(UUID.randomUUID().toString());
			card.setActiveStatus(true);
			card.setBillCycleLength(30);
			card.setCreateDate(LocalDate.now());
			card.setExpireDate(card.getCreateDate().plusYears(3));
			card.setNickname(signUpRequest.getNickname());
			card = cardRepo.save(card);
		} else {
			card = new CardEntity();
		}

		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setCardId(card.getCardId());
		return response;
	}

	private String generateCardNumber(){
		String majorIndustryIdentifier = "9";
		String issuerIdentificationNumber = "911-42";
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			if(i == 2 || i == 7 || i == 12){
				stringBuilder.append("-");
			}
			int number = random.nextInt(10);
			stringBuilder.append(number);
		}

		return majorIndustryIdentifier + issuerIdentificationNumber + stringBuilder;
	}

}
