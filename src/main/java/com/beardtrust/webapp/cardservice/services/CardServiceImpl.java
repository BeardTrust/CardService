package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepo;
	private final CardTypeRepository cardTypeRepo;

	@Autowired
	public CardServiceImpl(CardRepository cardRepo, CardTypeRepository cardTypeRepo) {
		this.cardRepo = cardRepo;
		this.cardTypeRepo = cardTypeRepo;
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

		if (result.isPresent()) {
			card = result.get();
		} else {
			throw new RuntimeException("Card id - " + id + " not found");
		}

		return card;
	}

	@Override
	@Transactional
	public void deleteById(String id) {

		Optional<CardEntity> result = cardRepo.findById(id);

		if (result.isPresent()) {
			cardRepo.deleteById(id);
		} else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
	}

	@Override
	@Transactional
	public void save(CardEntity card) {
		cardRepo.save(card);
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
