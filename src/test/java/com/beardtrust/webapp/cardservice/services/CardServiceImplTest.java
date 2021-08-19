package com.beardtrust.webapp.cardservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;

@ContextConfiguration(classes = {CardServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class CardServiceImplTest {
	
	@MockBean
	private CardRepository cardRepo;
	
	@Autowired
	private CardServiceImpl cardServiceImpl;
	
	@Autowired
	private CardService cardService;
	
	@MockBean
	private CardTypeRepository cardTypeRepo;
	
	private CardEntity cardEntity;
	
	private CardEntity cardEntity2;
	
	private CardRegistrationModel cardRegistrationModel;
	
	@BeforeEach
	public void testBeforeEach() {
		CardTypeEntity cardType = new CardTypeEntity("0", "credit_1", 0.02);
		LocalDate createDate = LocalDate.of(2021, 8, 14);
		LocalDate expireDate = LocalDate.of(2025, 8, 14);
		cardEntity = new CardEntity("1111-2222-3333",
				"1111-2222-3333",
				cardType,
				0.0,
				"1111-2222-3333-4444",
				0.0,
				createDate,
				"card_nickname",
				30,
				true,
				createDate);
		System.out.println(cardEntity.toString());
		
		cardRegistrationModel = new CardRegistrationModel();
		cardRegistrationModel.setUserId("1111-2222-3333");
		cardRegistrationModel.setCardType("1");
		cardRegistrationModel.setInterestRate(0.0f);
		cardRegistrationModel.setNickname("card_nickname");
		cardRegistrationModel.setBillCycleLength(30);
		
		CardSignUpResponseModel responseModel = this.cardService.registerCard(cardRegistrationModel.getUserId(), cardRegistrationModel);
		System.out.println(responseModel.toString());
		
		/*
		cardEntity.setUserId("1111-2222-3333");
		cardEntity.setCardId("1111-2222-3333");
		//cardEntity.setCardType("1");
		cardEntity.setInterestRate(0.0);
		cardEntity.setNickname("card_nickname");
		cardEntity.setBillCycleLength(30);
		//when(this.cardRepo.getById()).thenReturn(cardEntity);
		*/
	}
	
	@Test
	public void testGetAll() {
		/*
		List<CardEntity> cardList = cardService.getAll();
		System.out.println(cardList);
		assert(cardList.size() > 0);
		*/
		
		List<CardEntity> cardEntities = new ArrayList<CardEntity>();
		when(this.cardRepo.findAll()).thenReturn(cardEntities);
		List<CardEntity> actualCards = this.cardService.getAll();
		assertSame(cardEntities, actualCards);
		assertTrue(actualCards.isEmpty());
		verify(this.cardRepo).findAll();
		
	}
	
	
	@Test
	public void getById() {
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepo.findById(anyString())).thenReturn(ofResult);
		CardEntity actualCard = this.cardService.getById("Account id");
		assertSame(ofResult.get(), actualCard);
		//assertTrue(actualCard.isPresent());
		verify(this.cardRepo).findById(anyString());
		assertTrue(this.cardService.getAll().isEmpty());
	}
	
	
	@Test
	public void testRegisterCard() {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setUserId("1111-2222-3333");
		cardEntity.setCardId("1111-2222-3333");
		//cardEntity.setCardType("1");
		cardEntity.setInterestRate(0.0);
		cardEntity.setNickname("card_nickname");
		cardEntity.setBillCycleLength(30);
		//when(this.cardRepo.getById()).thenReturn(cardEntity);
		System.out.println("register");
		CardRegistrationModel cardRegistrationModel = new CardRegistrationModel();
		cardRegistrationModel.setUserId("1111-2222-3333");
		cardRegistrationModel.setCardType("1");
		cardRegistrationModel.setInterestRate(0.0f);
		cardRegistrationModel.setNickname("card_nickname");
		cardRegistrationModel.setBillCycleLength(30);
		
		System.out.println(cardRegistrationModel.getUserId());
		System.out.println(cardRegistrationModel.toString());
		
		CardSignUpResponseModel responseModel = this.cardServiceImpl.registerCard(cardRegistrationModel.getUserId(), cardRegistrationModel);
		//verify(this.cardRepo).findById(responseModel.getCardId());
		
		System.out.println("6");
		System.out.println(responseModel);
		
		Optional<CardEntity> optional = this.cardRepo.findById(responseModel.getCardId());
		System.out.println(optional.get());
		CardEntity card = optional.get();
		
		
		System.out.println(card.getCardId());
		
		System.out.println(cardEntity.getCardId());
		
		assertEquals(card.getCardId(), cardEntity.getCardId());
		

	}

}
