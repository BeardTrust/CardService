package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.apache.commons.lang.math.NumberUtils.isNumber;

/**
 * This class provides the implementation of the CardService interface.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 */
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

	@Override
	@Transactional
	public CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest) {
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(signUpRequest.getCardType());
		CardEntity card = new CardEntity();

		// Todo: Implement update user details logic

		if (cardType.isPresent()) {
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
			if (signUpRequest.getNickname().length() > 0) {
				card.setNickname(signUpRequest.getNickname());
			} else {
				card.setNickname(cardType.get().toString().toUpperCase() + "_CARD");
			}

			card = cardRepo.save(card);
			log.info("New card added to database " + card.getCardId());
		} else {
			log.error("Card type " + signUpRequest.getCardType() + " is not a valid card type");
			card = new CardEntity();
		}

		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setCardId(card.getCardId());
		return response;
	}

	@Override
	@Transactional
	public CardDTO getStatus(String userId, String cardId) {
		log.info("Card status request for " + userId);

		CardDTO status = null;
		Optional<CardEntity> card = cardRepo.findById(cardId);
		if (card.isPresent()) {
			if (card.get().getUserId().equals(userId)) {
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				status = modelMapper.map(card.get(), CardDTO.class);
			}
		}
		return status;
	}

	@Override
	public List<CardDTO> getCardsByUser(String userId) {
		List<CardDTO> returnValue = new ArrayList<>();
		List<CardEntity> cards = cardRepo.findAllByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		for (CardEntity card : cards) {
			CardDTO cardDTO = modelMapper.map(card, CardDTO.class);
			returnValue.add(cardDTO);
		}

		return returnValue;
	}

	@Override
	public Page<CardTypeDTO> getAvailableCardTypes(int pageNumber, int pageSize, String[] sortBy, String search) {
		Pageable page = null;

		List<Sort.Order> orders = new ArrayList<>();

		if(sortBy[0].contains(",")){
			for(String sortOrder : sortBy){
				String[] _sortBy = sortOrder.split(",");
				orders.add(new Sort.Order(getSortDirection(_sortBy[1]), _sortBy[0]));
			}
		} else {
			orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
		}

		page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<CardTypeEntity> cardTypes = null;

		if(search == null){
			cardTypes = cardTypeRepo.findAllByIsAvailable(true, page);
		} else {
			if(isNumber(search)){
				cardTypes = cardTypeRepo.findAllByIsAvailableTrueAndBaseInterestRateIsLike(Double.valueOf(search),
						page);
			} else {
				cardTypes =
						cardTypeRepo.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase(
								search, search, page);
			}
		}

		Page<CardTypeDTO> results = null;

		if(cardTypes.getTotalElements() > 0){
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			results = cardTypes.map((cardType) -> modelMapper.map(cardType,CardTypeDTO.class));
		} else {
			log.error("No available card types found");
		}

		return results;
	}

	/**
	 * This method generates a credit card number for a new credit card, looks for duplication in
	 * the database, and returns a unique card number using the specified major industry identifier
	 * and issuer identification number.
	 *
	 * @return String the string representation of the new credit card number
	 */
	private String generateCardNumber() {
		String majorIndustryIdentifier = "9";
		String issuerIdentificationNumber = "911-42";
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			if (i == 2 || i == 6) {
				stringBuilder.append("-");
			}
			int number = random.nextInt(10);
			stringBuilder.append(number);
		}

		String cardNumber = majorIndustryIdentifier + issuerIdentificationNumber + stringBuilder;

		return cardRepo.findById(cardNumber).isPresent() ? generateCardNumber() : cardNumber;
	}

	/**
	 * This method accepts a string and returns the intended sort direction, ascending or descending.
	 *
	 * @param direction String the string indicating the desired sort direction
	 * @return Sort.Direction the direction in which to sort
	 */
	private Sort.Direction getSortDirection(String direction){
		Sort.Direction returnValue = Sort.Direction.ASC;

		if(direction.equals("desc")){
			returnValue = Sort.Direction.DESC;
		}

		return returnValue;
	}
}
