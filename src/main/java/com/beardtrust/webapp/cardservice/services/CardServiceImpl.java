package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.entities.UserEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;
import com.beardtrust.webapp.cardservice.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

/**
 * This class provides the implementation of the CardService interface.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {
	private final CardRepository cardRepo;
	private final CardTypeRepository cardTypeRepo;
	private final UserRepository userRepository;

	/**
	 * This method returns a page of card DTOs.
	 *
	 * @param pageNumber int the page number
	 * @param pageSize   int the number of items on the page
	 * @param sortBy     String[] the sorting instructions
	 * @param search     String the search term
	 * @return the requested page
	 */
	@Override
	public Page<CardDTO> getAll(int pageNumber, int pageSize, String[] sortBy, String search) {
		log.trace("Start of CardService.getAll(" + pageNumber + ", " + pageSize + ", " + Arrays.toString(sortBy) +
				", " + search + ")");
		List<Sort.Order> orders = parseOrders(sortBy);

		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<CardDTO> response = null;

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Page<CardEntity> entities;

		if (search == null) {
			log.debug("Null search criteria in CardService.getAll(" + pageNumber +
					", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

			entities = cardRepo.findAll(page);
		} else {
			if (isCreatable(search)) {
				log.debug("Numerical search criteria in CardService.getAll(" + pageNumber +
						", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

				String[] values = search.split(",");

				CurrencyValue searchBalance;

				if (values.length == 2) {
					log.debug("Two numbers present in search criteria in CardService.getAll(" + pageNumber +
							", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

					searchBalance = new CurrencyValue(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
				} else {
					log.debug("One number present in search criteria in CardService.getAll(" + pageNumber +
							", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

					searchBalance = new CurrencyValue();
					searchBalance.setCents(Integer.parseInt(values[0]));
				}
				entities = cardRepo.findAllByBalanceOrInterestRateEquals(searchBalance, Double.valueOf(search), page);
			} else if (GenericValidator.isDate(search, "yyyy-MM-dd", true)) {
				log.debug("Date value present in search criteria in CardService.getAll(" + pageNumber +
						", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

				LocalDateTime dateSearch = LocalDateTime.parse(search);
				entities = cardRepo.findAllByCreateDateOrExpireDateEquals(dateSearch, dateSearch, page);
			} else {
				log.debug("String value present in search criteria in CardService.getAll(" + pageNumber +
						", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");
				entities =
						cardRepo.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(search,
								search, search, search, search, page);
			}
		}

		if (entities != null && entities.getTotalElements() > 0) {
			response = entities.map((entity) -> modelMapper.map(entity, CardDTO.class));
		} else {
			log.debug("No cards founds by CardService.getAll(" + pageNumber +
					", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");
		}

		log.trace("End of CardService.getAll(" + pageNumber + ", " + pageSize + ", " + Arrays.toString(sortBy) +
				", " + search + ")");

		return response;
	}

	/**
	 * This method returns a card entity by card id.
	 *
	 * @param id String the card UUID
	 * @return the card entity of the requested card
	 */
	@Override
	public CardEntity getById(String id) {
		log.trace("Start of CardService.getById(" + id + ")");

		Optional<CardEntity> result = cardRepo.findById(id);

		CardEntity card = null;

		if (result.isPresent()) {
			card = result.get();
		} else {
			log.debug("No card found by CardService.getById(" + id + ")");
		}

		log.trace("End of CardService.getById(" + id + ")");
		return card;
	}

	/**
	 * This method deactivates a card by its card id.
	 *
	 * @param id String card id
	 */
	@Override
	public void deactivateById(String id) {
		log.trace("Start of CardService.deactivateById(" + id + ")");

		Optional<CardEntity> result = cardRepo.findById(id);

		if (result.isPresent()) {
			cardRepo.deactivateById(id);
		} else {
			log.debug("No card found by CardService.deactivateById(" + id + ")");
		}

		log.trace("Start of CardService.deactivateById(" + id + ")");
	}

	/**
	 * This method takes a CardUpdateModel object and updates a card as stored in the
	 * database.
	 *
	 * @param cardUpdateModel the CardUpdateModel with the new card data
	 */
	@Override
	public void update(CardUpdateModel cardUpdateModel) {
		log.trace("Start of CardService.updateCard(<redacted CardUpdateModel for " +
				cardUpdateModel.getId() + ">)");

		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardUpdateModel.getCardType());
		Optional<UserEntity> user = userRepository.findById(cardUpdateModel.getUser());
		Optional<CardEntity> card = cardRepo.findById(cardUpdateModel.getId());

		if (card.isPresent()) {
			log.debug("Card found by CardService.updateCard(<redacted " +
					"CardUpdateModel for " + cardUpdateModel.getId() + ">)");

			card.get().setId(cardUpdateModel.getId());
			card.get().setUser(user.orElse(null));
			card.get().setCardType(cardType.orElse(null));
			card.get().setBalance(cardUpdateModel.getBalance());
			card.get().setCardNumber(cardUpdateModel.getCardNumber());
			card.get().setInterestRate(cardUpdateModel.getInterestRate());
			card.get().setCreateDate(cardUpdateModel.getCreateDate());
			card.get().setNickname(cardUpdateModel.getNickname());
			card.get().setBillCycleLength(cardUpdateModel.getBillCycleLength());
			card.get().setExpireDate(cardUpdateModel.getExpireDate());

			try {
				CardEntity result = cardRepo.save(card.get());
			} catch (Exception e) {
				log.error("CardService.updateCard(<redacted CardUpdateModel for " +
						cardUpdateModel.getId() + ">) failed.");
			}

		} else {
			log.debug("Unable to update card because no card was found by CardService.updateCard(<redacted " +
					"CardUpdateModel for " + cardUpdateModel.getId() + ">)");
		}

		log.trace("End of CardService.updateCard(<redacted CardUpdateModel for " +
				cardUpdateModel.getId() + ">)");
	}

	/**
	 * This method takes a user's id and a CardRegistrationModel object and registers a
	 * new card to the user.
	 *
	 * @param userId           String the user's id
	 * @param cardRegistration CardRegistrationModel containing the registration data
	 * @return CardSignUpResponseModel representing the response to the registration request
	 */
	@Override
	public CardSignUpResponseModel registerCard(String userId, CardRegistrationModel cardRegistration) {
		log.trace("Start of CardService.registerCard(" + userId + ", <redacted CardRegistrationModel>)");
		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(cardRegistration.getCardType());
		CardEntity card = new CardEntity();

		if (cardType.isPresent()) {
			Optional<UserEntity> user = userRepository.findById(cardRegistration.getUser());
			card.setBalance(new CurrencyValue(0, 0));
			card.setCardType(cardType.get());
			card.setInterestRate(cardType.get().getBaseInterestRate() + cardRegistration.getInterestRate());
			card.setUser(user.orElse(null));
			card.setCardNumber(generateCardNumber());
			card.setId(UUID.randomUUID().toString());
			card.setActiveStatus(true);
			card.setBillCycleLength(cardRegistration.getBillCycleLength());
			card.setCreateDate(LocalDateTime.now());
			card.setExpireDate(card.getCreateDate().plusYears(3));
			card.setNickname(cardRegistration.getNickname());
			card = cardRepo.save(card);
		} else {
			log.debug("Invalid card type requested in CardService.registerCard(" + userId + ", <redacted " +
					"CardRegistrationModel>)");
		}

		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setId(card.getId());

		log.trace("End of CardService.registerCard(" + userId + ", <redacted CardRegistrationModel>)");

		return response;
	}

	/**
	 * This method receives an application for a credit card, processes that application, and
	 * returns a CardSignUpResponseModel object containing relevant information from the
	 * application process.
	 *
	 * @param userId        String the user ID of the applying user
	 * @param signUpRequest SignUpRequestModel the application for the credit card
	 * @return CardSignUpResponseModel the response object created during the application process
	 */
	@Override
	public CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest) {
		log.trace("Start of CardService.applyForCard(" + userId + ", <redacted CardSignUpRequestModel>)");

		Optional<CardTypeEntity> cardType = cardTypeRepo.findById(signUpRequest.getCardType());
		CardEntity card = new CardEntity();

		// Todo: Implement update user details logic

		if (cardType.isPresent()) {
			Optional<UserEntity> user = userRepository.findById(userId);
			card.setBalance(new CurrencyValue(0, 0));
			card.setCardType(cardType.get());
			card.setInterestRate(cardType.get().getBaseInterestRate());
			card.setUser(user.orElse(null));
			card.setCardNumber(generateCardNumber());
			card.setId(UUID.randomUUID().toString());
			card.setActiveStatus(true);
			card.setBillCycleLength(30);
			card.setCreateDate(LocalDateTime.now());
			card.setExpireDate(card.getCreateDate().plusYears(3));
			if (signUpRequest.getNickname().length() > 0) {
				card.setNickname(signUpRequest.getNickname());
			} else {
				card.setNickname(cardType.get().toString().toUpperCase() + "_CARD");
			}

			card = cardRepo.save(card);
		} else {
			log.debug("Invalid card type requested by CardService.applyForCard(" + userId + ", <redacted " +
					"CardSignUpRequestModel>)");

			card = new CardEntity();
		}

		CardSignUpResponseModel response = new CardSignUpResponseModel();
		response.setId(card.getId());

		log.trace("End of CardService.applyForCard(" + userId + ", <redacted CardSignUpRequestModel>)");

		return response;
	}

	/**
	 * This method retrieves card information and returns a card DTO to send
	 * card status information to an authorized party.
	 *
	 * @param userId String the user ID of the card owner
	 * @param id     String the card ID of the desired card
	 * @return CardDTO the data transfer object containing required card information
	 */
	@Override
	public CardDTO getStatus(String userId, String id) {
		log.trace("Start of CardService.getStatus(" + userId + ", " + id + ")");
		CardDTO status = null;
		Optional<CardEntity> card = cardRepo.findById(id);

		if (card.isPresent()) {
			if (card.get().getUser().getUserId().equals(userId)) {
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				status = modelMapper.map(card.get(), CardDTO.class);
			} else {
				log.debug("User id of cardholder does not match user id provided by CardService." +
						"getStatus(" + userId + ", " + id + ")");
			}
		} else {
			log.debug("No card found by CardService.getStatus(" + userId + ", " + id + ")");
		}

		log.trace("End of CardService.getStatus(" + userId + ", " + id + ")");
		return status;
	}

	/**
	 * This method receives a user's id as a string parameter and uses it to retrieve
	 * a list of card entities associated with that user id, then creates a list of
	 * card data transfer objects from the list of card entities and returns the list
	 * of card data transfer objects.
	 *
	 * @param userId         String the user's id
	 * @param pageNumber     int the requested page number
	 * @param pageSize       int the number of items to display per page
	 * @param sortBy         String[] the array of sorting orders
	 * @param searchCriteria String the matching criteria
	 * @return Page<CardDTO> the page of cards conforming to the provided specifications
	 */
	@Override
	public Page<CardDTO> getCardsByUser(String userId, int pageNumber, int pageSize, String[] sortBy,
										String searchCriteria) {
		log.trace("Start of CardService.getCardsByUser(" + userId + ", " + pageNumber + ", " + pageSize +
				", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

		Page<CardEntity> cards = null;
		Pageable page;

		List<Sort.Order> orders = parseOrders(sortBy);

		page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

		CurrencyValue searchBalance;

		if (searchCriteria == null) {
			log.debug("Null value in search criteria of CardService.getCardsByUser(" + userId + ", " + pageNumber +
					", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

			Optional<UserEntity> user = userRepository.findById(userId);
			if (user.isPresent()) {
				cards = cardRepo.findAllByUser(user.get(), page);
				for (CardEntity card : cards) {
					System.out.println(card);
				}
			} else {
				log.debug("No user found by CardService.getCardsByUser(" + userId + ", " + pageNumber + ", "
						+ pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");
			}

		} else if (isCreatable(searchCriteria)) {
			log.debug("Numerical value in search criteria of CardService.getCardsByUser(" + userId + ", " + pageNumber + ", "
					+ pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

			searchBalance = parseBalance(searchCriteria);

			cards = cardRepo.findAllByUser_UserIdEqualsAndBalanceOrBillCycleLengthOrInterestRateEquals(userId,
					searchBalance, Integer.parseInt(searchCriteria), Double.parseDouble(searchCriteria), page);

		} else if (GenericValidator.isDate(searchCriteria, "yyyy-MM-dd", true)) {
			log.debug("Date value in search criteria of CardService.getCardsByUser(" + userId + ", " + pageNumber + ", "
					+ pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

			LocalDateTime dateSearch = LocalDateTime.parse(searchCriteria);
			cards = cardRepo.findAllByUser_UserIdEqualsAndCreateDateOrExpireDateEquals(userId, dateSearch, dateSearch,
					page);
		} else {
			log.debug("String value in search criteria of CardService.getCardsByUser(" + userId + ", " + pageNumber +
					", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

			cards =
					cardRepo.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(userId,
							searchCriteria, searchCriteria, searchCriteria, page);
		}

		Page<CardDTO> returnValue = null;

		if (cards != null && cards.getTotalElements() > 0) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

			returnValue = cards.map((card) -> modelMapper.map(card, CardDTO.class));
		} else {
			log.debug("No cards found by CardService.getCardsByUser(" + userId + ", " + pageNumber + ", "
					+ pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");
		}

		log.trace("End of CardService.getCardsByUser(" + userId + ", " + pageNumber + ", " + pageSize +
				", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

		return returnValue;
	}

	/**
	 * This method retrieves a list of all currently available card types from the
	 * database and returns them as a Page of CardTypeDTOs of the specified size
	 * and placement in the sequence of pages sorted by the provided criteria of
	 * field and direction.
	 *
	 * @return List<CardTypeDTO> list of all available card types
	 */
	@Override
	public Page<CardTypeDTO> getAvailableCardTypes(int pageNumber, int pageSize, String[] sortBy, String search) {
		log.trace("Start of CardService.getAvailableCardTypes(" + pageNumber + ", " + pageSize +
				", " + Arrays.toString(sortBy) + ", " + search + ")");

		Pageable page;

		List<Sort.Order> orders = parseOrders(sortBy);

		page = PageRequest.of(pageNumber, pageSize, Sort.by(orders));
		Page<CardTypeEntity> cardTypes;

		if (search == null) {
			log.debug("Null value in search criteria of CardService.getAvailableCardTypes(" + pageNumber +
					", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

			cardTypes = cardTypeRepo.findAllByIsAvailableTrue(page);
		} else {
			if (isCreatable(search)) {
				log.debug("Numerical value in search criteria of CardService.getAvailableCardTypes(" + pageNumber +
						", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

				cardTypes = cardTypeRepo.findAllByIsAvailableTrueAndBaseInterestRateIsLike(Double.valueOf(search),
						page);
			} else {
				log.debug("String value in search criteria of CardService.getAvailableCardTypes(" + pageNumber +
						", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + search + ")");

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
			log.debug("No card types found by CardService.getAvailableCardTypes(" + pageNumber + ", " + pageSize +
					", " + Arrays.toString(sortBy) + ", " + search + ")");
		}

		log.trace("End of CardService.getAvailableCardTypes(" + pageNumber + ", " + pageSize +
				", " + Arrays.toString(sortBy) + ", " + search + ")");

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
		log.trace("Start of CardService.generateCardNumber()");

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

		Optional<CardEntity> card = cardRepo.findById(cardNumber);

		if (card.isPresent()) {
			log.debug("Recursive invocation; duplicate number generated by CardService.generateCardNumber()");
			cardNumber = generateCardNumber();
		}

		log.trace("End of CardService.generateCardNumber()");

		return cardNumber;
	}

	/**
	 * This method accepts a string and returns the intended sort direction, ascending or descending.
	 *
	 * @param direction String the string indicating the desired sort direction
	 * @return Sort.Direction the direction in which to sort
	 */
	private Sort.Direction getSortDirection(String direction) {
		log.trace("Start of CardService.getSortDirection(" + direction + ")");

		Sort.Direction returnValue = Sort.Direction.ASC;

		if (direction.equals("desc")) {
			returnValue = Sort.Direction.DESC;
		}

		log.trace("End of CardService.getSortDirection(" + direction + ")");
		return returnValue;
	}

	/**
	 * This method takes an array of strings, sortBy, and parses that string to produce
	 * a list of sort orders.
	 *
	 * @param sortBy String[] the string of sorting instructions
	 * @return List<Sort.Order> the parsed collection of sorting instructions
	 */
	private List<Sort.Order> parseOrders(String[] sortBy) {
		log.trace("Start of CardService.parseOrders(" + Arrays.toString(sortBy) + ")");

		List<Sort.Order> orders = new ArrayList<>();

		if (sortBy[0].contains(",")) {
			for (String sortOrder : sortBy) {
				String[] _sortBy = sortOrder.split(",");
				orders.add(new Sort.Order(getSortDirection(_sortBy[1]), _sortBy[0]));
			}
		} else {
			orders.add(new Sort.Order(getSortDirection(sortBy[1]), sortBy[0]));
		}

		log.trace("End of CardService.parseOrders(" + Arrays.toString(sortBy) + ")");

		return orders;
	}

	/**
	 * This method takes a String argument and attempts to parse it into a
	 * CurrencyValue object.
	 *
	 * @param searchCriteria a String representing some currency value
	 * @return the CurrencyValue object representing the currency value
	 */
	private CurrencyValue parseBalance(String searchCriteria) {
		log.trace("Start of CardService.parseBalance(" + searchCriteria + ")");

		String[] values = searchCriteria.split(",");
		CurrencyValue searchBalance;

		if (values.length == 2) {
			log.debug("Two numerical values in search criteria of CardService.parseBalance(" + searchCriteria + ")");

			searchBalance = new CurrencyValue(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
		} else {
			if (Integer.parseInt(values[0]) > 99) {
				log.debug("Numerical values in search criteria of CardService.parseBalance(" + searchCriteria +
						") exceeds possible value for cents field.");

				searchBalance = new CurrencyValue(Integer.parseInt(values[0]), 0);
			} else {
				log.debug("Cent value provided in search criteria of CardService.parseBalance(" + searchCriteria + ")");

				searchBalance = new CurrencyValue(0, Integer.parseInt(values[0]));
			}
		}

		log.trace("End of CardService.parseBalance(" + searchCriteria + ")");

		return searchBalance;
	}
}
