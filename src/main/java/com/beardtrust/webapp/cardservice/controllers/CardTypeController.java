package com.beardtrust.webapp.cardservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.services.CardTypeService;

@RestController
@RequestMapping("/cardtypes/")
public class CardTypeController {
	
	private CardTypeService cardTypeService;
	
	@Autowired
	public CardTypeController(CardTypeService cardTypeService) {
		this.cardTypeService = cardTypeService;
	}
	
	@PostMapping()
	@PreAuthorize("permitAll()")
	public void createCardType(@RequestBody CardTypeEntity cardType) {
		cardTypeService.save(cardType);
	}
	
	@GetMapping()
	@PreAuthorize("permitAll()")
	public List<CardTypeEntity> displayAllCardTypes(){
		return cardTypeService.getAll();
	}

}
