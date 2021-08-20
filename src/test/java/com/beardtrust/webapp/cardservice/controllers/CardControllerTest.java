package com.beardtrust.webapp.cardservice.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.services.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = {CardController.class} )
@ExtendWith(SpringExtension.class)
//@Import(SecurityConfig.class)
public class CardControllerTest {
	
	@Autowired
	private CardController cardController;
	
	@MockBean
	private CardService cardService;
	
	@Test
	public void testRegisterCard() throws Exception{
		CardRegistrationModel cardRegistrationModel = new CardRegistrationModel();
		cardRegistrationModel.setUserId("1123-3948-9807");
		cardRegistrationModel.setCardType("0");
		cardRegistrationModel.setInterestRate(0.001f);
		cardRegistrationModel.setNickname("card_nickname");
		cardRegistrationModel.setBillCycleLength(30);
		String content = (new ObjectMapper()).writeValueAsString(cardRegistrationModel);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cards/register/{id}", "1123-3948-9807")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cardController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	public void testDisplayAllCards() throws Exception {
		when(this.cardService.getAll()).thenReturn(new ArrayList<CardEntity>());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cards");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cardController)
		.build()
		.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("<List/>"));
	}
	
	@Test
	public void testDisplayCardById() throws Exception{
		when(this.cardService.getById("1123-3948-9807")).thenReturn(new CardEntity());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cards/{id}", "1123-3948-9807");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cardController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void testUpdateCard() throws Exception{
		//when(this.cardService.update((CardUpdateModel) any())).thenReturn("foo");

		CardUpdateModel cardUpdateModel = new CardUpdateModel();
		cardUpdateModel.setCardId("1123-3948-9807");
		cardUpdateModel.setUserId("1123-0987-4657");
		cardUpdateModel.setCardType("0");
		cardUpdateModel.setBalance(0.0);
		cardUpdateModel.setCardNumber("4563-9870-4567-1923");
		cardUpdateModel.setInterestRate(0.0);
		cardUpdateModel.setCreateDate(null);
		cardUpdateModel.setNickname("card_nickname");
		cardUpdateModel.setBillCycleLength(30);
		cardUpdateModel.setActiveStatus(true);
		cardUpdateModel.setExpireDate(null);
		String content = (new ObjectMapper()).writeValueAsString(cardUpdateModel);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cards/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cardController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testDeactivateCard() throws Exception{
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cards/{id}", "1123-3948-9807");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cardController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
