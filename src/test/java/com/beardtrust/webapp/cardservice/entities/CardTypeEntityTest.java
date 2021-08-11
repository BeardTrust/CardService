package com.beardtrust.webapp.cardservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTypeEntityTest {
	@Test
	public void testConstructor() {
		CardTypeEntity actualCardTypeEntity = new CardTypeEntity();
		actualCardTypeEntity.setBaseInterestRate(10.0);
		actualCardTypeEntity.setId("42");
		actualCardTypeEntity.setTypeName("Type Name");
		assertEquals(10.0, actualCardTypeEntity.getBaseInterestRate().doubleValue());
		assertEquals("42", actualCardTypeEntity.getId());
		assertEquals("Type Name", actualCardTypeEntity.getTypeName());
		assertEquals("Type Name", actualCardTypeEntity.toString());
	}
}

