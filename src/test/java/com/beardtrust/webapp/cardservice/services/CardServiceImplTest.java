package com.beardtrust.webapp.cardservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CardServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class CardServiceImplTest {
	@MockBean
	private CardRepository cardRepository;

	@Autowired
	private CardServiceImpl cardServiceImpl;

	@MockBean
	private CardTypeRepository cardTypeRepository;

	@Test
	public void testConstructor() {
		assertTrue((new CardServiceImpl(mock(CardRepository.class), mock(CardTypeRepository.class))).getAll().isEmpty());
	}

	@Test
	public void testGetAll() {
		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		when(this.cardRepository.findAll()).thenReturn(cardEntityList);
		List<CardEntity> actualAll = this.cardServiceImpl.getAll();
		assertSame(cardEntityList, actualAll);
		assertTrue(actualAll.isEmpty());
		verify(this.cardRepository).findAll();
	}

	@Test
	public void testGetById() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		assertSame(cardEntity, this.cardServiceImpl.getById("42"));
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetById2() {
		when(this.cardRepository.findById(anyString())).thenReturn(Optional.<CardEntity>empty());
		assertThrows(RuntimeException.class, () -> this.cardServiceImpl.getById("42"));
		verify(this.cardRepository).findById(anyString());
	}

	@Test
	public void testDeleteById() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		doNothing().when(this.cardRepository).deleteById(anyString());
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		this.cardServiceImpl.deleteById("42");
		verify(this.cardRepository).deleteById(anyString());
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testDeleteById2() {
		doNothing().when(this.cardRepository).deleteById(anyString());
		when(this.cardRepository.findById(anyString())).thenReturn(Optional.<CardEntity>empty());
		assertThrows(RuntimeException.class, () -> this.cardServiceImpl.deleteById("42"));
		verify(this.cardRepository).findById(anyString());
	}

	@Test
	public void testSave() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		when(this.cardRepository.save((CardEntity) any())).thenReturn(cardEntity);

		CardTypeEntity cardTypeEntity1 = new CardTypeEntity();
		cardTypeEntity1.setBaseInterestRate(10.0);
		cardTypeEntity1.setId("42");
		cardTypeEntity1.setTypeName("Type Name");

		CardEntity cardEntity1 = new CardEntity();
		cardEntity1.setBalance(10.0);
		cardEntity1.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity1.setNickname("Nickname");
		cardEntity1.setUserId("42");
		cardEntity1.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity1.setCardId("42");
		cardEntity1.setBillCycleLength(3);
		cardEntity1.setInterestRate(10.0);
		cardEntity1.setCardType(cardTypeEntity1);
		cardEntity1.setActiveStatus(true);
		cardEntity1.setCardNumber("Card Number");
		this.cardServiceImpl.save(cardEntity1);
		verify(this.cardRepository).save((CardEntity) any());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testApplyForCard() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		Optional<CardTypeEntity> ofResult = Optional.<CardTypeEntity>of(cardTypeEntity);
		when(this.cardTypeRepository.findById(anyString())).thenReturn(ofResult);
		when(this.cardRepository.findById(anyString())).thenThrow(new RuntimeException("An error occurred"));

		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");
		assertThrows(RuntimeException.class, () -> this.cardServiceImpl.applyForCard("42", cardSignUpRequestModel));
		verify(this.cardTypeRepository).findById(anyString());
		verify(this.cardRepository).findById(anyString());
	}

	@Test
	public void testGetStatus() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		CardDTO actualStatus = this.cardServiceImpl.getStatus("42", "42");
		assertTrue(actualStatus.getActiveStatus());
		assertEquals("42", actualStatus.getUserId());
		assertEquals("Nickname", actualStatus.getNickname());
		assertEquals(10.0f, actualStatus.getInterestRate().floatValue());
		assertEquals("1970-01-02", actualStatus.getExpireDate());
		assertEquals("1970-01-02", actualStatus.getCreateDate());
		assertEquals("Type Name", actualStatus.getCardType());
		assertEquals("Card Number", actualStatus.getCardNumber());
		assertEquals("42", actualStatus.getCardId());
		assertEquals(3, actualStatus.getBillCycleLength().intValue());
		assertEquals(10.0f, actualStatus.getBalance().floatValue());
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetStatus2() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofYearDay(1, 1));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		CardDTO actualStatus = this.cardServiceImpl.getStatus("42", "42");
		assertTrue(actualStatus.getActiveStatus());
		assertEquals("42", actualStatus.getUserId());
		assertEquals("Nickname", actualStatus.getNickname());
		assertEquals(10.0f, actualStatus.getInterestRate().floatValue());
		assertEquals("0001-01-01", actualStatus.getExpireDate());
		assertEquals("1970-01-02", actualStatus.getCreateDate());
		assertEquals("Type Name", actualStatus.getCardType());
		assertEquals("Card Number", actualStatus.getCardNumber());
		assertEquals("42", actualStatus.getCardId());
		assertEquals(3, actualStatus.getBillCycleLength().intValue());
		assertEquals(10.0f, actualStatus.getBalance().floatValue());
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetStatus3() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(null);
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		CardDTO actualStatus = this.cardServiceImpl.getStatus("42", "42");
		assertTrue(actualStatus.getActiveStatus());
		assertEquals("42", actualStatus.getUserId());
		assertEquals("Nickname", actualStatus.getNickname());
		assertEquals(10.0f, actualStatus.getInterestRate().floatValue());
		assertNull(actualStatus.getExpireDate());
		assertEquals("1970-01-02", actualStatus.getCreateDate());
		assertEquals("Type Name", actualStatus.getCardType());
		assertEquals("Card Number", actualStatus.getCardNumber());
		assertEquals("42", actualStatus.getCardId());
		assertEquals(3, actualStatus.getBillCycleLength().intValue());
		assertEquals(10.0f, actualStatus.getBalance().floatValue());
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetStatus4() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("User Id");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById(anyString())).thenReturn(ofResult);
		assertNull(this.cardServiceImpl.getStatus("42", "42"));
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetStatus5() {
		when(this.cardRepository.findById(anyString())).thenReturn(Optional.<CardEntity>empty());
		assertNull(this.cardServiceImpl.getStatus("42", "42"));
		verify(this.cardRepository).findById(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetCardsByUser() {
		when(this.cardRepository.findAllByUserId(anyString())).thenReturn(new ArrayList<CardEntity>());
		assertTrue(this.cardServiceImpl.getCardsByUser("42").isEmpty());
		verify(this.cardRepository).findAllByUserId(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetCardsByUser2() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity);
		when(this.cardRepository.findAllByUserId(anyString())).thenReturn(cardEntityList);
		assertEquals(1, this.cardServiceImpl.getCardsByUser("42").size());
		verify(this.cardRepository).findAllByUserId(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetCardsByUser3() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");

		CardTypeEntity cardTypeEntity1 = new CardTypeEntity();
		cardTypeEntity1.setBaseInterestRate(10.0);
		cardTypeEntity1.setId("42");
		cardTypeEntity1.setTypeName("Type Name");

		CardEntity cardEntity1 = new CardEntity();
		cardEntity1.setBalance(10.0);
		cardEntity1.setExpireDate(LocalDate.ofEpochDay(1L));
		cardEntity1.setNickname("Nickname");
		cardEntity1.setUserId("42");
		cardEntity1.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity1.setCardId("42");
		cardEntity1.setBillCycleLength(3);
		cardEntity1.setInterestRate(10.0);
		cardEntity1.setCardType(cardTypeEntity1);
		cardEntity1.setActiveStatus(true);
		cardEntity1.setCardNumber("Card Number");

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity1);
		cardEntityList.add(cardEntity);
		when(this.cardRepository.findAllByUserId(anyString())).thenReturn(cardEntityList);
		assertEquals(2, this.cardServiceImpl.getCardsByUser("42").size());
		verify(this.cardRepository).findAllByUserId(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}

	@Test
	public void testGetCardsByUser4() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(10.0);
		cardEntity.setExpireDate(null);
		cardEntity.setNickname("Nickname");
		cardEntity.setUserId("42");
		cardEntity.setCreateDate(LocalDate.ofEpochDay(1L));
		cardEntity.setCardId("42");
		cardEntity.setBillCycleLength(3);
		cardEntity.setInterestRate(10.0);
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setActiveStatus(true);
		cardEntity.setCardNumber("Card Number");

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity);
		when(this.cardRepository.findAllByUserId(anyString())).thenReturn(cardEntityList);
		assertEquals(1, this.cardServiceImpl.getCardsByUser("42").size());
		verify(this.cardRepository).findAllByUserId(anyString());
		assertTrue(this.cardServiceImpl.getAll().isEmpty());
	}
}

