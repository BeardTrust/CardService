package com.beardtrust.webapp.cardservice.controllers;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.services.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardtypes/")
public class CardTypeController {

	private final CardTypeService cardTypeService;

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
	public List<CardTypeEntity> displayAllCardTypes() {
		return cardTypeService.getAll();
	}

}
