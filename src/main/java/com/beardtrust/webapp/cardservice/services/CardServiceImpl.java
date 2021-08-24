package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.Balance;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
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
	public Page<CardDTO> getAll(int pageNumber, int pageSize, String[] sortBy, String search) {
		List<Sort.Order> orders = parseOrders(sortBy);

		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<CardDTO> response = null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Page<CardEntity> entities = null;

		if (search == null) {
			entities = cardRepo.findAll(page);
		} else {
			if (isNumber(search)) {
				String[] values = search.split(",");
				if (values.length == 2) {
					Balance searchBalance = new Balance(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
				} else {
					Balance searchBalance = new Balance(Integer.parseInt(values[0]));
				}
			} else if (GenericValidator.isDate(search, "yyyy-MM-dd", true)) {
				LocalDate dateSearch = LocalDate.parse(search);
				entities = cardRepo.findAllByCreateDateOrExpireDateIsLike(dateSearch, dateSearch, page);
			} else {
				entities =
						cardRepo.findAllByCardIdOrUserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(search,
								search, search, search, search, page);
			}
		}

		if (entities.getTotalElements() > 0) {
			response = entities.map((entity) -> modelMapper.map(entity, CardDTO.class));
		} else {
			log.error("No cards found");
		}

		return response;
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
	public void deactivateById(String id) {

		Optional<CardEntity> result = cardRepo.findById(id);

		if (result.isPresent()) {
			cardRepo.deactivateById(id);
			log.info("Card id - " + id + " has been deactivated");
		} else {
			throw new RuntimeException("Card id - " + id + " not found");
		}
	}

	@Override
	@Transactional
	public void update(CardUpdateModel cardUpdateModel) {
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardUpdateModel.getCardType());

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
		System.out.println(card);
		try {
			CardEntity result = cardRepo.save(card.get());
			//log.info("Card id - " + card.getCardId() + " has been saved");
		} catch (Exception e) {
			System.out.println("Could not save card");
		}
	}

	@Override
	@Transactional
	public CardSignUpResponseModel registerCard(String userId, CardRegistrationModel cardRegistration) {
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardRegistration.getCardType());
		CardEntity card = new CardEntity();

		if (cardType.isPresent()) {
			card.setBalance(new Balance(0, 0));
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
	public CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest) {
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(signUpRequest.getCardType());
		CardEntity card = new CardEntity();

		// Todo: Implement update user details logic

		if (cardType.isPresent()) {
			card.setBalance(new Balance(0, 0));
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
	public Page<CardDTO> getCardsByUser(String userId, int pageNumber, int pageSize, String[] sortBy,
										String searchCriteria) {
		Page<CardEntity> cards = null;
		Pageable page = null;

		List<Sort.Order> orders = parseOrders(sortBy);

		page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

		Balance searchBalance = null;

		if (searchCriteria == null) {
			cards = cardRepo.findAllByUserId(userId, page);
		} else if (isNumber(searchCriteria)) {
				searchBalance = parseBalance(searchCriteria);

				cards = cardRepo.findAllByUserIdEqualsAndBalanceOrBillCycleLengthOrInterestRateIsLike(userId,
				searchBalance, Integer.parseInt(searchCriteria), Double.parseDouble(searchCriteria), page);

			} else if (GenericValidator.isDate(searchCriteria, "yyyy-MM-dd", true)) {
				LocalDate dateSearch = LocalDate.parse(searchCriteria);
				cards = cardRepo.findAllByUserIdEqualsAndCreateDateOrExpireDateIsLike(userId, dateSearch, dateSearch,
						page);
			} else {
				cards =
						cardRepo.findAllByUserIdEqualsAndCardIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(userId,
								searchCriteria, searchCriteria, searchCriteria, page);
			}

		Page<CardDTO> returnValue = null;

		if (cards.getTotalElements() > 0) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			returnValue = cards.map((card) -> modelMapper.map(card, CardDTO.class));
		}

		return returnValue;
	}

	@Override
	public Page<CardTypeDTO> getAvailableCardTypes(int pageNumber, int pageSize, String[] sortBy, String search) {
		Pageable page = null;

		List<Sort.Order> orders = parseOrders(sortBy);

		page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<CardTypeEntity> cardTypes = null;

		if (search == null) {
			cardTypes = cardTypeRepo.findAllByIsAvailableTrue(page);
		} else {
			if (isNumber(search)) {
				cardTypes = cardTypeRepo.findAllByIsAvailableTrueAndBaseInterestRateIsLike(Double.valueOf(search),
						page);
			} else {
				cardTypes =
						cardTypeRepo.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase(
								search, search, page);
			}
		}

		Page<CardTypeDTO> results = null;

		if (cardTypes.getTotalElements() > 0) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			results = cardTypes.map((cardType) -> modelMapper.map(cardType, CardTypeDTO.class));
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
	private Sort.Direction getSortDirection(String direction) {
		Sort.Direction returnValue = Sort.Direction.ASC;

		if (direction.equals("desc")) {
			returnValue = Sort.Direction.DESC;
		}

		return returnValue;
	}

	/**
	 * This method takes an array of strings, sortBy, and parses that string to produce
	 * a list of sort orders to use.
	 *
	 * @param sortBy String[] the string of sorting instructions
	 * @return List<Sort.Order> the parsed collection of sorting instructions
	 */
	private List<Sort.Order> parseOrders(String[] sortBy) {
		List<Sort.Order> orders = new ArrayList<>();

		if (sortBy[0].contains(",")) {
			for (String sortOrder : sortBy) {
				String[] _sortBy = sortOrder.split(",");
				orders.add(new Sort.Order(getSortDirection(_sortBy[1]), _sortBy[0]));
			}
		} else {
			orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
		}

		return orders;
	}

	private Balance parseBalance(String searchCriteria) {
		String[] values = searchCriteria.split(",");
		Balance searchBalance = null;

		if (values.length == 2) {
			searchBalance = new Balance(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
		} else {
			if(Integer.parseInt(values[0]) > 99){
				searchBalance = new Balance(Integer.parseInt(values[0]), 0);
			} else {
				searchBalance = new Balance(Integer.parseInt(values[0]));
			}
		}

		return searchBalance;
	}
}
