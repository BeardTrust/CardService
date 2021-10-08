package com.beardtrust.webapp.cardservice.controllers;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.services.CardTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cardtypes/")
@Slf4j
public class CardTypeController {

	private final CardTypeService cardTypeService;

	@PostMapping()
	@PreAuthorize("permitAll()")
	public void createCardType(@RequestBody CardTypeEntity cardType) {
		log.trace("Start CardTypeController.createCardType(" + cardType + ")");
		cardTypeService.save(cardType);
		log.trace("End CardTypeController.createCardType(" + cardType + ")");
	}

	@GetMapping()
	@PreAuthorize("permitAll()")
	public List<CardTypeEntity> displayAllCardTypes() {
		log.trace("Start CardTypeController.displayAllCardTypes()");
		log.trace("End CardTypeController.displayAllCardTypes()");
		return cardTypeService.getAll();
	}

}
