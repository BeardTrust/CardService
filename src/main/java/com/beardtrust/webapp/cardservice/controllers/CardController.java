package com.beardtrust.webapp.cardservice.controllers;

import java.util.List;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.services.CardService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("/cards")
@Slf4j
public class CardController {
	
	private CardService cardService;
	
	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	@PostMapping()
	//@PreAuthorize("hasAuthority('admin')")
	public void createUser(@RequestBody CardEntity card) {
		cardService.save(card);
	}
	
	@GetMapping()
	//@PreAuthorize("hasAuthority('admin')")
	public List<CardEntity> displayAllUsers(){
		return cardService.getAll();
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin') or principal == #id")
	public CardEntity displayUserById(@PathVariable String id) {
		return cardService.getById(id);
	}

	@PutMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public void updateUser(@RequestBody CardEntity card, @PathVariable String id) {
		
		cardService.save(card);
	}
	
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public void deleteUser(@PathVariable String id){
		cardService.deleteById(id);
	}


	/**
	 * This method receives a CardDTO object and invokes the card service to sign the specified user
	 * up for the specified card.
	 *
	 * @param userId String the user's unique id
	 * @param signUpRequest CardSignUpRequestModel object containing application data
	 * @return ResponseEntity<CardDTO> the response from the server including the new card's dto
	 */
	@PreAuthorize("permitAll()")
	@PostMapping(path = "/{id}")
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardSignUpResponseModel> applyForCard(@PathVariable("id") String userId,
																@RequestBody CardSignUpRequestModel signUpRequest){
		ResponseEntity<CardSignUpResponseModel> response = null;
		log.info("New card application for " + userId);
		response = new ResponseEntity<>(cardService.applyForCard(userId, signUpRequest), HttpStatus.CREATED);
		return response;
	}

	@PreAuthorize("permitAll()")
	@GetMapping(path = "/{id}/{cardId}")
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardDTO> getCardStatus(@PathVariable("id") String userId, @PathVariable("cardId") String
												 cardId){
		ResponseEntity<CardDTO> response = null;
		log.info("Card status report for " + cardId);
		CardDTO status = cardService.getStatus(userId, cardId);
		response = new ResponseEntity<>(status, HttpStatus.OK);
		return response;
	}
}
