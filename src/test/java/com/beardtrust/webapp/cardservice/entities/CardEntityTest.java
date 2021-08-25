package com.beardtrust.webapp.cardservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CardEntityTest {
	@Test
	public void testConstructor() {
		CardEntity actualCardEntity = new CardEntity();
		actualCardEntity.setActiveStatus(true);
		actualCardEntity.setBalance(new Balance(10, 0));
		actualCardEntity.setBillCycleLength(3);
		actualCardEntity.setCardId("42");
		actualCardEntity.setCardNumber("Card Number");
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		actualCardEntity.setCardType(cardTypeEntity);
		LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
		actualCardEntity.setCreateDate(ofEpochDayResult);
		LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
		actualCardEntity.setExpireDate(ofEpochDayResult1);
		actualCardEntity.setInterestRate(10.0);
		actualCardEntity.setNickname("Nickname");
		actualCardEntity.setUserId("42");
		assertTrue(actualCardEntity.getActiveStatus());
		assertEquals("10.00", actualCardEntity.getBalance().toString());
		assertEquals(3, actualCardEntity.getBillCycleLength().intValue());
		assertEquals("42", actualCardEntity.getCardId());
		assertEquals("Card Number", actualCardEntity.getCardNumber());
		assertSame(cardTypeEntity, actualCardEntity.getCardType());
		assertSame(ofEpochDayResult, actualCardEntity.getCreateDate());
		assertSame(ofEpochDayResult1, actualCardEntity.getExpireDate());
		assertEquals(10.0, actualCardEntity.getInterestRate().doubleValue());
		assertEquals("Nickname", actualCardEntity.getNickname());
		assertEquals("42", actualCardEntity.getUserId());
		assertEquals("CardEntity [cardId=42, userId=42, cardType=Type Name, balance=10.0, cardNumber=Card Number,"
				+ " interestRate=10.0, createDate=1970-01-02, nickname=Nickname, billCycleLength=3, activeStatus=true,"
				+ " expireDate=1970-01-02]", actualCardEntity.toString());
	}
}

