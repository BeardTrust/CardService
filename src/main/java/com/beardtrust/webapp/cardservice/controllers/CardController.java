package com.beardtrust.webapp.cardservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/cards")
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
  
}
