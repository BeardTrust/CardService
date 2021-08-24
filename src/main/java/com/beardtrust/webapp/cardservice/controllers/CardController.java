package com.beardtrust.webapp.cardservice.controllers;


import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

/**
 * This class provides the rest api endpoints and associated logic.
 *
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@RestController
@RequestMapping("/cards")
@Slf4j
public class CardController {

	private final CardService cardService;

	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@PreAuthorize("permitAll()")
	@PostMapping(path = "/register/{id}")
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardSignUpResponseModel> registerCard(@PathVariable("id") String userId,
																@RequestBody CardRegistrationModel cardRegistration) {
		ResponseEntity<CardSignUpResponseModel> response = null;
		System.out.println(userId);
		System.out.println("Signup Request: " + cardRegistration);
		response = new ResponseEntity<>(cardService.registerCard(userId, cardRegistration), HttpStatus.CREATED);
		return response;
	}

	@GetMapping()
	//@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Page<CardDTO>> displayAllCards(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
														 @RequestParam(name = "size", defaultValue = "10") int pageSize,
														 @RequestParam(name = "sortBy",
																 defaultValue = "cardId,asc") String[] sortBy,
														 @RequestParam(name = "search", required = false) String searchCriteria) {
		ResponseEntity<Page<CardDTO>> response = null;

		response = new ResponseEntity<>(cardService.getAll(pageNumber, pageSize, sortBy, searchCriteria),
				HttpStatus.OK);

		return response;
	}

	@GetMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	@PreAuthorize("permitAll()")
	public CardEntity displayCardById(@PathVariable String id) {
		return cardService.getById(id);
	}

	@PutMapping()
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasAuthority('admin')")
	public void updateCard(@RequestBody CardUpdateModel cardUpdateModel) {
		System.out.println("updateCard");
		cardService.update(cardUpdateModel);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasAuthority('admin')")
	public void deactivateCard(@PathVariable String id) {
		cardService.deactivateById(id);
	}


	/**
	 * This method receives a CardDTO object and invokes the card service to sign the specified user
	 * up for the specified card.
	 *
	 * @param userId        String the user's unique id
	 * @param signUpRequest CardSignUpRequestModel object containing application data
	 * @return ResponseEntity<CardDTO> the response from the server including the new card's dto
	 */
	@PreAuthorize("permitAll()")
	@PostMapping(path = "/{id}")
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardSignUpResponseModel> applyForCard(@PathVariable("id") String userId,
																@RequestBody CardSignUpRequestModel signUpRequest) {
		ResponseEntity<CardSignUpResponseModel> response = null;
		log.info("New card application for " + userId);
		response = new ResponseEntity<>(cardService.applyForCard(userId, signUpRequest), HttpStatus.CREATED);
		return response;
	}

	/**
	 * this method retrieves the details of a single card associated with a specific
	 * user id from the database.
	 *
	 * @param userId String the user id that must be associated with the card
	 * @param cardId String the card id to search for
	 * @return ResponseEntity<CardDTO> the http response entity with the card details
	 */
	@PreAuthorize("permitAll()")
	@GetMapping(path = "/{id}/{cardId}")
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardDTO> getCardStatus(@PathVariable("id") String userId,
												 @PathVariable("cardId") String cardId) {
		ResponseEntity<CardDTO> response = null;
		log.info("Card status report for " + cardId);
		CardDTO status = cardService.getStatus(userId, cardId);
		response = new ResponseEntity<>(status, HttpStatus.OK);
		return response;
	}

	/**
	 * This method retrieves a list of all cards associated with a user id from the database.
	 *
	 * @param userId String the user id to search for
	 * @return ResponseEntity<List < CardDTO>> the http response with the list of all associated cards
	 */
	@PreAuthorize("permitAll()")
	@GetMapping(path = "/{id}/all")
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<CardDTO>> getCardsByUser(@PathVariable("id") String userId,
														@RequestParam(name = "page", defaultValue = "0") int pageNumber,
														@RequestParam(name = "size", defaultValue = "10") int pageSize,
														@RequestParam(name = "sortBy",
																defaultValue = "cardId,asc") String[] sortBy,
														@RequestParam(name = "search", required = false) String searchCriteria) {
		ResponseEntity<Page<CardDTO>> response = null;
		log.info("Card list requested for " + userId);
		Page<CardDTO> cards = null;
		cards = cardService.getCardsByUser(userId, pageNumber, pageSize, sortBy, searchCriteria);
		response = new ResponseEntity<>(cards, HttpStatus.OK);
		return response;
	}

	/**
	 * This method exposes the card service's method for getting all available card types to the
	 * /cards/available endpoint.
	 *
	 * @return ResponseEntity<List < CardTypeDTO>> list of all currently available card types
	 */
	@PreAuthorize("permitAll()")
	@GetMapping(path = "/available")
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Page<CardTypeDTO>> getAvailableCardTypes(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
																   @RequestParam(name = "size", defaultValue = "10") int pageSize,
																   @RequestParam(name = "sortBy",
																		   defaultValue = "id,desc") String[] sortBy,
																   @RequestParam(name = "search", required = false) String searchCriteria) {
		Page<CardTypeDTO> page = cardService.getAvailableCardTypes(pageNumber, pageSize, sortBy, searchCriteria);

		log.info("Request received to view all available cards");
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
}
