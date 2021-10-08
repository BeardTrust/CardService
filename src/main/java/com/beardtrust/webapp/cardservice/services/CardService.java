package com.beardtrust.webapp.cardservice.services;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CardService {

	/**
	 * This method returns a page of card DTOs.
	 *
	 * @param pageNumber int the page number
	 * @param pageSize int the number of items on the page
	 * @param sortBy String[] the sorting instructions
	 * @param search String the search term
	 * @return the requested page
	 */
	Page<CardDTO> getAll(int pageNumber, int pageSize, String[] sortBy, String search);

	/**
	 * This method returns a card entity by card id.
	 *
	 * @param id String the card UUID
	 * @return the card entity of the requested card
	 */
	CardEntity getById(String id);

	/**
	 * This method deactivates a card by its card id.
	 *
	 * @param id String card id
	 */
	void deactivateById(String id);

	/**
	 * This method takes a CardUpdateModel object and updates a card as stored in the
	 * database.
	 *
	 * @param cardUpdateModel the CardUpdateModel with the new card data
	 */
	void update(CardUpdateModel cardUpdateModel);

	/**
	 * This method takes a user's id and a CardRegistrationModel object and registers a
	 * new card to the user.
	 *
	 * @param userId String the user's id
	 * @param cardRegistration CardRegistrationModel containing the registration data
	 * @return CardSignUpResponseModel representing the response to the registration request
	 */
	CardSignUpResponseModel registerCard(String userId, CardRegistrationModel cardRegistration);

	/**
	 * This method receives an application for a credit card, processes that application, and
	 * returns a CardSignUpResponseModel object containing relevant information from the
	 * application process.
	 *
	 * @param userId        String the user ID of the applying user
	 * @param signUpRequest SignUpRequestModel the application for the credit card
	 * @return CardSignUpResponseModel the response object created during the application process
	 */
	CardSignUpResponseModel applyForCard(String userId, CardSignUpRequestModel signUpRequest);

	/**
	 * This method retrieves card information and returns a card DTO to send
	 * card status information to an authorized party.
	 *
	 * @param userId String the user ID of the card owner
	 * @param id String the card ID of the desired card
	 * @return CardDTO the data transfer object containing required card information
	 */
	CardDTO getStatus(String userId, String id);

	/**
	 * This method receives a user's id as a string parameter and uses it to retrieve
	 * a list of card entities associated with that user id, then creates a list of
	 * card data transfer objects from the list of card entities and returns the list
	 * of card data transfer objects.
	 *
	 * @param userId String the user's id
	 * @param pageNumber int the requested page number
	 * @param pageSize int the number of items to display per page
	 * @param sortBy String[] the array of sorting orders
	 * @param searchCriteria String the matching criteria
	 * @return Page<CardDTO> the page of cards conforming to the provided specifications
	 */
	Page<CardDTO> getCardsByUser(String userId, int pageNumber, int pageSize, String[] sortBy, String searchCriteria);

	/**
	 * This method retrieves a list of all currently available card types from the
	 * database and returns them as a Page of CardTypeDTOs of the specified size
	 * and placement in the sequence of pages sorted by the provided criteria of
	 * field and direction.
	 *
	 * @return List<CardTypeDTO> list of all available card types
	 */
	Page<CardTypeDTO> getAvailableCardTypes(int pageNumber, int pageSize, String[] sortBy, String search);
}
