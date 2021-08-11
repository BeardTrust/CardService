package com.beardtrust.webapp.cardservice.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CardDTOTest {
	@Test
	public void testCanEqual() {
		assertFalse((new CardDTO()).canEqual("Other"));
	}

	@Test
	public void testCanEqual2() {
		CardDTO cardDTO = new CardDTO();

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertTrue(cardDTO.canEqual(cardDTO1));
	}

	@Test
	public void testConstructor() {
		CardDTO actualCardDTO = new CardDTO();
		actualCardDTO.setActiveStatus(true);
		actualCardDTO.setBalance(10.0f);
		actualCardDTO.setBillCycleLength(3);
		actualCardDTO.setCardId("42");
		actualCardDTO.setCardNumber("Card Number");
		actualCardDTO.setCardType("Card Type");
		actualCardDTO.setCreateDate("2020-03-01");
		actualCardDTO.setExpireDate("2020-03-01");
		actualCardDTO.setInterestRate(10.0f);
		actualCardDTO.setNickname("Nickname");
		actualCardDTO.setUserId("42");
		assertTrue(actualCardDTO.getActiveStatus());
		assertEquals(10.0f, actualCardDTO.getBalance().floatValue());
		assertEquals(3, actualCardDTO.getBillCycleLength().intValue());
		assertEquals("42", actualCardDTO.getCardId());
		assertEquals("Card Number", actualCardDTO.getCardNumber());
		assertEquals("Card Type", actualCardDTO.getCardType());
		assertEquals("2020-03-01", actualCardDTO.getCreateDate());
		assertEquals("2020-03-01", actualCardDTO.getExpireDate());
		assertEquals(10.0f, actualCardDTO.getInterestRate().floatValue());
		assertEquals("Nickname", actualCardDTO.getNickname());
		assertEquals("42", actualCardDTO.getUserId());
		assertEquals(
				"CardDTO(cardId=42, userId=42, cardType=Card Type, balance=10.0, cardNumber=Card Number, interestRate=10.0,"
						+ " createDate=2020-03-01, nickname=Nickname, billCycleLength=3, activeStatus=true, expireDate=2020-03"
						+ "-01)",
				actualCardDTO.toString());
	}

	@Test
	public void testEquals() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(null));
	}

	@Test
	public void testEquals10() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("Card Type");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals11() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(0);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals12() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(null);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals13() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("42");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals14() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType(null);
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals15() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(false);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals16() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(null);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals17() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(null);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals18() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(0.5f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals19() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("42");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals2() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals("Different type to CardDTO"));
	}

	@Test
	public void testEquals20() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber(null);
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals21() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020/03/01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals22() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate(null);
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals23() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(null);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals24() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(0.5f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals25() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020/03/01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals26() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate(null);

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals27() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname(null);
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname(null);
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertTrue(cardDTO.equals(cardDTO1));
		int expectedHashCodeResult = cardDTO.hashCode();
		assertEquals(expectedHashCodeResult, cardDTO1.hashCode());
	}

	@Test
	public void testEquals28() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId(null);
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId(null);
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertTrue(cardDTO.equals(cardDTO1));
		int expectedHashCodeResult = cardDTO.hashCode();
		assertEquals(expectedHashCodeResult, cardDTO1.hashCode());
	}

	@Test
	public void testEquals3() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");
		assertTrue(cardDTO.equals(cardDTO));
		int expectedHashCodeResult = cardDTO.hashCode();
		assertEquals(expectedHashCodeResult, cardDTO.hashCode());
	}

	@Test
	public void testEquals4() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertTrue(cardDTO.equals(cardDTO1));
		int expectedHashCodeResult = cardDTO.hashCode();
		assertEquals(expectedHashCodeResult, cardDTO1.hashCode());
	}

	@Test
	public void testEquals5() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("42");
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals6() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname(null);
		cardDTO.setUserId("42");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals7() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId(null);
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals8() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("Card Type");
		cardDTO.setCardId("42");
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}

	@Test
	public void testEquals9() {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setNickname("Nickname");
		cardDTO.setUserId("42");
		cardDTO.setCardId(null);
		cardDTO.setBillCycleLength(3);
		cardDTO.setCardType("Card Type");
		cardDTO.setActiveStatus(true);
		cardDTO.setInterestRate(10.0f);
		cardDTO.setCardNumber("Card Number");
		cardDTO.setCreateDate("2020-03-01");
		cardDTO.setBalance(10.0f);
		cardDTO.setExpireDate("2020-03-01");

		CardDTO cardDTO1 = new CardDTO();
		cardDTO1.setNickname("Nickname");
		cardDTO1.setUserId("42");
		cardDTO1.setCardId("42");
		cardDTO1.setBillCycleLength(3);
		cardDTO1.setCardType("Card Type");
		cardDTO1.setActiveStatus(true);
		cardDTO1.setInterestRate(10.0f);
		cardDTO1.setCardNumber("Card Number");
		cardDTO1.setCreateDate("2020-03-01");
		cardDTO1.setBalance(10.0f);
		cardDTO1.setExpireDate("2020-03-01");
		assertFalse(cardDTO.equals(cardDTO1));
	}
}

