package com.beardtrust.webapp.cardservice.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

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
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.services.CardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cards")
public class CardController {
	
	private CardService cardService;
	
	@Autowired
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	/*
	@PostMapping()
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<CardRegistrationResponse> registerCard(@RequestBody CardRegistration body) {
		ResponseEntity<CardRegistrationResponse> response = null;
		CardRegistrationResponse registrationResponse = new CardRegistrationResponse();
	}
	*/
	@PreAuthorize("permitAll()")
	@PostMapping(path = "/{id}")
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardSignUpResponseModel> applyForCard(@PathVariable("id") String userId,
																@RequestBody CardSignUpRequestModel signUpRequest){
		ResponseEntity<CardSignUpResponseModel> response = null;
		System.out.println(userId);
		System.out.println("Signup Request: " + signUpRequest);
		response = new ResponseEntity<>(cardService.applyForCard(userId, signUpRequest), HttpStatus.CREATED);
		return response;
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping(path = "/register/{id}")
	@Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<CardSignUpResponseModel> registerCard(@PathVariable("id") String userId,
																@RequestBody CardRegistrationModel cardRegistration){
		ResponseEntity<CardSignUpResponseModel> response = null;
		System.out.println(userId);
		System.out.println("Signup Request: " + cardRegistration);
		response = new ResponseEntity<>(cardService.registerCard(userId, cardRegistration), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping()
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasAuthority('admin')")
	public List<CardEntity> displayAllCards(){
		System.out.println("displayCards");
		return cardService.getAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("permitAll()")
	//@PreAuthorize("hasAuthority('admin') or principal == #id")
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
	public void deactivateCard(@PathVariable String id){
		
		cardService.deactivateById(id);
	}
  
}
