package com.beardtrust.webapp.cardservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CardSignUpRequestModelTest {
	@Test
	public void testCanEqual() {
		assertFalse((new CardSignUpRequestModel()).canEqual("Other"));
	}

	@Test
	public void testCanEqual2() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertTrue(cardSignUpRequestModel.canEqual(cardSignUpRequestModel1));
	}

	@Test
	public void testConstructor() {
		CardSignUpRequestModel actualCardSignUpRequestModel = new CardSignUpRequestModel();
		actualCardSignUpRequestModel.setCardType("Card Type");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant());
		actualCardSignUpRequestModel.setDateOfBirth(fromResult);
		actualCardSignUpRequestModel.setEmail("jane.doe@example.org");
		actualCardSignUpRequestModel.setFirstName("Jane");
		actualCardSignUpRequestModel.setLastName("Doe");
		actualCardSignUpRequestModel.setNickname("Nickname");
		actualCardSignUpRequestModel.setPhone("4105551212");
		actualCardSignUpRequestModel.setRole("Role");
		assertEquals("Card Type", actualCardSignUpRequestModel.getCardType());
		assertSame(fromResult, actualCardSignUpRequestModel.getDateOfBirth());
		assertEquals("jane.doe@example.org", actualCardSignUpRequestModel.getEmail());
		assertEquals("Jane", actualCardSignUpRequestModel.getFirstName());
		assertEquals("Doe", actualCardSignUpRequestModel.getLastName());
		assertEquals("Nickname", actualCardSignUpRequestModel.getNickname());
		assertEquals("4105551212", actualCardSignUpRequestModel.getPhone());
		assertEquals("Role", actualCardSignUpRequestModel.getRole());
		/*assertEquals(
				"CardSignUpRequestModel(cardType=Card Type, email=jane.doe@example.org, firstName=Jane, lastName=Doe,"
						+ " phone=4105551212, role=Role, dateOfBirth=Thu Jan 01 00:00:00 CST 1970, nickname=Nickname)",
				actualCardSignUpRequestModel.toString());*/
	}

	@Test
	public void testEquals() {
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
		assertFalse(cardSignUpRequestModel.equals(null));
	}

	@Test
	public void testEquals10() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		cardSignUpRequestModel.setDateOfBirth(null);
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals11() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Card Type");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals12() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole(null);
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals13() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Card Type");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals14() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname(null);
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals15() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType(null);
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals16() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("jane.doe@example.org");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals17() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("+44 1865 4960636");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals18() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone(null);
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals19() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Card Type");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals2() {
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
		assertFalse(cardSignUpRequestModel.equals("Different type to CardSignUpRequestModel"));
	}

	@Test
	public void testEquals20() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName(null);

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals3() {
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
		assertTrue(cardSignUpRequestModel.equals(cardSignUpRequestModel));
		int expectedHashCodeResult = cardSignUpRequestModel.hashCode();
		assertEquals(expectedHashCodeResult, cardSignUpRequestModel.hashCode());
	}

	@Test
	public void testEquals4() {
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

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertTrue(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
		int expectedHashCodeResult = cardSignUpRequestModel.hashCode();
		assertEquals(expectedHashCodeResult, cardSignUpRequestModel1.hashCode());
	}

	@Test
	public void testEquals5() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Card Type");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals6() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName(null);
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals7() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("Card Type");
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals8() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail(null);
		LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}

	@Test
	public void testEquals9() {
		CardSignUpRequestModel cardSignUpRequestModel = new CardSignUpRequestModel();
		cardSignUpRequestModel.setLastName("Doe");
		cardSignUpRequestModel.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();
		cardSignUpRequestModel.setDateOfBirth(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel.setRole("Role");
		cardSignUpRequestModel.setNickname("Nickname");
		cardSignUpRequestModel.setCardType("Card Type");
		cardSignUpRequestModel.setPhone("4105551212");
		cardSignUpRequestModel.setFirstName("Jane");

		CardSignUpRequestModel cardSignUpRequestModel1 = new CardSignUpRequestModel();
		cardSignUpRequestModel1.setLastName("Doe");
		cardSignUpRequestModel1.setEmail("jane.doe@example.org");
		LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
		cardSignUpRequestModel1.setDateOfBirth(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
		cardSignUpRequestModel1.setRole("Role");
		cardSignUpRequestModel1.setNickname("Nickname");
		cardSignUpRequestModel1.setCardType("Card Type");
		cardSignUpRequestModel1.setPhone("4105551212");
		cardSignUpRequestModel1.setFirstName("Jane");
		assertFalse(cardSignUpRequestModel.equals(cardSignUpRequestModel1));
	}
}

