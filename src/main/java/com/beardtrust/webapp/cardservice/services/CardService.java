package com.beardtrust.webapp.cardservice.services;

import java.util.List;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import org.springframework.data.domain.Page;

public interface CardService {
	
	public List<CardEntity> getAll();
	
	public CardEntity getById(String id);
	
	public void deleteById(String id);
	
	public void save(CardEntity card);

	/**
	 * This method receives an application for a credit card, processes that application, and
	 * returns a CardSignUpResponseModel object containing relevant information from the
	 * application process.
	 *
	 * @param userId String the user ID of the applying user
	 * @param signUpRequest SignUpRequestModel the application for the credit card
	 * @return CardSignUpResponseModel the response object created during the application process
	 */
	CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest);

	/**
	 * This method retrieves card information and returns a card DTO to send
	 * card status information to an authorized party.
	 *
	 * @param userId String the user ID of the card owner
	 * @param cardId String the card ID of the desired card
	 * @return CardDTO the data transfer object containing required card information
	 */
	CardDTO getStatus(String userId, String cardId);

	/**
	 * This method receives a user's id as a string parameter and uses it to retrieve
	 * a list of card entities associated with that user id, then creates a list of
	 * card data transfer objects from the list of card entities and returns the list
	 * of card data transfer objects.
	 *
	 * @param userId String the userId of the user whose cards are requested
	 * @return List<CardDTO> the list of all cards associated with user as CardDTOs
	 */
	List<CardDTO> getCardsByUser(String userId);

	/**
	 * This method retrieves a list of all currently available card types from the
	 * database and returns them as a Page of CardTypeDTOs of the specified size
	 * and placement in the sequence of pages sorted by the provided criteria of
	 * field and direction.
	 *
	 * @return List<CardTypeDTO> list of all available card types
	 */
	Page<CardTypeDTO> getAvailableCardTypes(int pageNumber, int pageSize, String[] sortBy);
}
