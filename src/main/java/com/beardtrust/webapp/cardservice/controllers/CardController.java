package com.beardtrust.webapp.cardservice.controllers;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

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

	@PostMapping()
	//@PreAuthorize("hasAuthority('admin')")
	public void createCard(@RequestBody CardEntity card) {
		cardService.save(card);
	}

	@GetMapping()
	//@PreAuthorize("hasAuthority('admin')")
	public List<CardEntity> displayAllCards() {
		return cardService.getAll();
	}

	@GetMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public CardEntity displayCardById(@PathVariable String id) {
		return cardService.getById(id);
	}

	@PutMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public void updateCard(@RequestBody CardEntity card, @PathVariable String id) {

		cardService.save(card);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public void deleteCard(@PathVariable String id) {
		cardService.deleteById(id);
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
	public ResponseEntity<List<CardDTO>> getCardsByUser(@PathVariable("id") String userId) {
		ResponseEntity<List<CardDTO>> response = null;
		log.info("Card list requested for " + userId);
		List<CardDTO> cards = null;
		cards = cardService.getCardsByUser(userId);
		response = new ResponseEntity<>(cards, HttpStatus.OK);
		return response;
	}
}
