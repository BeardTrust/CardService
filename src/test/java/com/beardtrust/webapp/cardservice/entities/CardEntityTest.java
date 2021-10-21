package com.beardtrust.webapp.cardservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class CardEntityTest {
	@Test
	public void testConstructor() {
		CardEntity actualCardEntity = new CardEntity();
		actualCardEntity.setActiveStatus(true);
		actualCardEntity.setBalance(new CurrencyValue(10, 0));
		actualCardEntity.setBillCycleLength(3);
		actualCardEntity.setId("42");
		actualCardEntity.setCardNumber("Card Number");
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		actualCardEntity.setCardType(cardTypeEntity);
		LocalDateTime ofEpochDayResult = LocalDateTime.of(LocalDate.ofEpochDay(1L), LocalTime.of(0,0,0));
		actualCardEntity.setCreateDate(ofEpochDayResult);
		LocalDateTime ofEpochDayResult1 = LocalDateTime.of(LocalDate.ofEpochDay(1L), LocalTime.of(0,0,0));
		actualCardEntity.setExpireDate(ofEpochDayResult1);
		actualCardEntity.setInterestRate(10.0);
		actualCardEntity.setNickname("Nickname");
		UserEntity user = new UserEntity();
		user.setUserId("42");
		actualCardEntity.setUser(user);
		assertTrue(actualCardEntity.isActiveStatus());
		assertEquals("$10.00", actualCardEntity.getBalance().toString());
		assertEquals(3, actualCardEntity.getBillCycleLength());
		assertEquals("42", actualCardEntity.getId());
		assertEquals("Card Number", actualCardEntity.getCardNumber());
		assertSame(cardTypeEntity, actualCardEntity.getCardType());
		assertSame(ofEpochDayResult, actualCardEntity.getCreateDate());
		assertSame(ofEpochDayResult1, actualCardEntity.getExpireDate());
		assertEquals(10.0, actualCardEntity.getInterestRate());
		assertEquals("Nickname", actualCardEntity.getNickname());
		assertEquals("42", actualCardEntity.getUser().getUserId());
		assertEquals("Card Number", actualCardEntity.toString());
	}
}

