package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
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

	/**
	 * This method receives an application for a credit card, processes that application, and
	 * returns a CardSignUpResponseModel object containing relevant information from the
	 * application process.
	 *
	 * @param userId String the user ID of the applying user
	 * @param signUpRequest SignUpRequestModel the application for the credit card
	 * @return CardSignUpResponseModel the response object created during the application process
	 */
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
			log.info("New card added to database " + card.getCardNumber());
		} else {
			log.error("Card type " + signUpRequest.getCardType() + " is not a valid card type");
			card = new CardEntity();
		}

		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setCardId(card.getCardId());
		return response;
	}

	/**
	 * This method retrieves card information and returns a card DTO to send
	 * card status information to an authorized party.
	 *
	 * @param userId String the user ID of the card owner
	 * @param cardId String the card ID of the desired card
	 * @return CardDTO the data transfer object containing required card information
	 */
	@Override
	@Transactional
	public CardDTO getStatus(String userId, String cardId) {
		log.info("Card status request for " + userId);

		CardDTO status = null;
		Optional<CardEntity> card = cardRepo.findById(cardId);
		if(card.isPresent()){
			if(card.get().getUserId().equals(userId)){
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				status = modelMapper.map(card.get(), CardDTO.class);
			}
		}
		return status;
	}

	/**
	 * This method generates a credit card number for a new credit card, looks for duplication in
	 * the database, and returns a unique card number using the specified major industry identifier
	 * and issuer identification number.
	 *
	 * @return String the string representation of the new credit card number
	 */
	private String generateCardNumber(){
		String majorIndustryIdentifier = "9";
		String issuerIdentificationNumber = "911-42";
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			if(i == 2 || i == 6){
				stringBuilder.append("-");
			}
			int number = random.nextInt(10);
			stringBuilder.append(number);
		}

		String cardNumber = majorIndustryIdentifier + issuerIdentificationNumber + stringBuilder;

		return cardRepo.findById(cardNumber).isPresent() ? generateCardNumber() : cardNumber;
	}
}
