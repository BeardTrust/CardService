package com.beardtrust.webapp.cardservice.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CardSignUpResponseModelTest {
	@Test
	public void testCanEqual() {
		assertFalse((new CardSignUpResponseModel()).canEqual("Other"));
	}

	@Test
	public void testCanEqual2() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();

		CardSignUpResponseModel cardSignUpResponseModel1 = new CardSignUpResponseModel();
		cardSignUpResponseModel1.setId("42");
		assertTrue(cardSignUpResponseModel.canEqual(cardSignUpResponseModel1));
	}

	@Test
	public void testConstructor() {
		CardSignUpResponseModel actualCardSignUpResponseModel = new CardSignUpResponseModel();
		actualCardSignUpResponseModel.setId("42");
		assertEquals("42", actualCardSignUpResponseModel.getId());
		assertEquals("CardSignUpResponseModel(id=42)", actualCardSignUpResponseModel.toString());
	}

	@Test
	public void testEquals() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId("42");
		assertFalse(cardSignUpResponseModel.equals(null));
	}

	@Test
	public void testEquals2() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId("42");
		assertFalse(cardSignUpResponseModel.equals("Different type to CardSignUpResponseModel"));
	}

	@Test
	public void testEquals3() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId("42");
		assertTrue(cardSignUpResponseModel.equals(cardSignUpResponseModel));
		int expectedHashCodeResult = cardSignUpResponseModel.hashCode();
		assertEquals(expectedHashCodeResult, cardSignUpResponseModel.hashCode());
	}

	@Test
	public void testEquals4() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId("42");

		CardSignUpResponseModel cardSignUpResponseModel1 = new CardSignUpResponseModel();
		cardSignUpResponseModel1.setId("42");
		assertTrue(cardSignUpResponseModel.equals(cardSignUpResponseModel1));
		int expectedHashCodeResult = cardSignUpResponseModel.hashCode();
		assertEquals(expectedHashCodeResult, cardSignUpResponseModel1.hashCode());
	}

	@Test
	public void testEquals5() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId(null);

		CardSignUpResponseModel cardSignUpResponseModel1 = new CardSignUpResponseModel();
		cardSignUpResponseModel1.setId("42");
		assertFalse(cardSignUpResponseModel.equals(cardSignUpResponseModel1));
	}

	@Test
	public void testEquals6() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId("Card Id");

		CardSignUpResponseModel cardSignUpResponseModel1 = new CardSignUpResponseModel();
		cardSignUpResponseModel1.setId("42");
		assertFalse(cardSignUpResponseModel.equals(cardSignUpResponseModel1));
	}

	@Test
	public void testEquals7() {
		CardSignUpResponseModel cardSignUpResponseModel = new CardSignUpResponseModel();
		cardSignUpResponseModel.setId(null);

		CardSignUpResponseModel cardSignUpResponseModel1 = new CardSignUpResponseModel();
		cardSignUpResponseModel1.setId(null);
		assertTrue(cardSignUpResponseModel.equals(cardSignUpResponseModel1));
		int expectedHashCodeResult = cardSignUpResponseModel.hashCode();
		assertEquals(expectedHashCodeResult, cardSignUpResponseModel1.hashCode());
	}
}

