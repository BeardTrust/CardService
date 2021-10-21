package com.beardtrust.webapp.cardservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.entities.UserEntity;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.repos.CardRepository;
import com.beardtrust.webapp.cardservice.repos.CardTransactionRepository;
import com.beardtrust.webapp.cardservice.repos.CardTypeRepository;
import com.beardtrust.webapp.cardservice.repos.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CardServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CardServiceImplTest {
	@MockBean
	private CardRepository cardRepository;

	@Autowired
	private CardServiceImpl cardServiceImpl;

	@MockBean
	private CardTransactionRepository cardTransactionRepository;

	@MockBean
	private CardTypeRepository cardTypeRepository;

	@MockBean
	private UserRepository userRepository;

	@Test
	void testGetAll() {
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAll(10, 3, new String[]{"Sort By"}, "Search"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAll(10, 3, new String[]{","}, "Search"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAll(10, 3, new String[]{}, "Search"));
	}

	@Test
	void testGetAll2() {
		when(this.cardRepository.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardEntity>(new ArrayList<CardEntity>()));
		assertNull(this.cardServiceImpl.getAll(10, 3, new String[]{"Sort By", "Sort By"}, "Search"));
		verify(this.cardRepository).findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAll3() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole(",");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname(",");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(0);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity);
		PageImpl<CardEntity> pageImpl = new PageImpl<CardEntity>(cardEntityList);
		when(this.cardRepository.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(1, this.cardServiceImpl.getAll(10, 3, new String[]{"Sort By", "Sort By"}, "Search").toList().size());
		verify(this.cardRepository).findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAll4() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole(",");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname(",");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(0);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(0);
		currencyValue1.setCents(0);
		currencyValue1.setNegative(true);

		CardTypeEntity cardTypeEntity1 = new CardTypeEntity();
		cardTypeEntity1.setBaseInterestRate(10.0);
		cardTypeEntity1.setAvailable(true);
		cardTypeEntity1.setId("42");
		cardTypeEntity1.setTypeName(",");
		cardTypeEntity1.setDescription("The characteristics of someone or something");
		cardTypeEntity1.setPreviewURL("https://example.org/example");

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole(",");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUserId("42");
		userEntity1.setUsername("janedoe");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		CardEntity cardEntity1 = new CardEntity();
		cardEntity1.setBalance(currencyValue1);
		cardEntity1.setNickname(",");
		cardEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity1.setId("42");
		cardEntity1.setCardType(cardTypeEntity1);
		cardEntity1.setBillCycleLength(0);
		cardEntity1.setInterestRate(10.0);
		cardEntity1.setUser(userEntity1);
		cardEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity1.setCardNumber("42");
		cardEntity1.setActiveStatus(true);

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity1);
		cardEntityList.add(cardEntity);
		PageImpl<CardEntity> pageImpl = new PageImpl<CardEntity>(cardEntityList);
		when(this.cardRepository.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(2, this.cardServiceImpl.getAll(10, 3, new String[]{"Sort By", "Sort By"}, "Search").toList().size());
		verify(this.cardRepository).findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAll5() {
		when(this.cardRepository.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(null);
		assertNull(this.cardServiceImpl.getAll(10, 3, new String[]{"Sort By", "Sort By"}, "Search"));
		verify(this.cardRepository).findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAll6() {
		when(this.cardRepository.findAllByIdOrUser_UserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardEntity>(new ArrayList<CardEntity>()));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAll(-1, 3, new String[]{","}, "Search"));
	}

	@Test
	void testGetById() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname("Nickname");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(1);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById((String) any())).thenReturn(ofResult);
		assertSame(cardEntity, this.cardServiceImpl.getById("42"));
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetById2() {
		when(this.cardRepository.findById((String) any())).thenReturn(Optional.<CardEntity>empty());
		assertThrows(RuntimeException.class, () -> this.cardServiceImpl.getById("42"));
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testDeactivateById() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname("Nickname");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(1);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		doNothing().when(this.cardRepository).deactivateById((String) any());
		when(this.cardRepository.findById((String) any())).thenReturn(ofResult);
		this.cardServiceImpl.deactivateById("42");
		verify(this.cardRepository).deactivateById((String) any());
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testUpdate() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.userRepository.findById((String) any())).thenReturn(ofResult);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");
		Optional<CardTypeEntity> ofResult1 = Optional.<CardTypeEntity>of(cardTypeEntity);
		when(this.cardTypeRepository.findById((String) any())).thenReturn(ofResult1);
		when(this.cardRepository.findById((String) any())).thenThrow(new RuntimeException("An error occurred"));
		assertThrows(RuntimeException.class, () -> this.cardServiceImpl.update(new CardUpdateModel()));
		verify(this.userRepository).findById((String) any());
		verify(this.cardTypeRepository).findById((String) any());
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testUpdate2() {
		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");
		Optional<UserEntity> ofResult = Optional.<UserEntity>of(userEntity);
		when(this.userRepository.findById((String) any())).thenReturn(ofResult);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");
		Optional<CardTypeEntity> ofResult1 = Optional.<CardTypeEntity>of(cardTypeEntity);
		when(this.cardTypeRepository.findById((String) any())).thenReturn(ofResult1);
		when(this.cardRepository.findById((String) any())).thenReturn(Optional.<CardEntity>empty());
		this.cardServiceImpl.update(new CardUpdateModel());
		verify(this.userRepository).findById((String) any());
		verify(this.cardTypeRepository).findById((String) any());
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetStatus() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname("Nickname");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(1);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById((String) any())).thenReturn(ofResult);
		CardDTO actualStatus = this.cardServiceImpl.getStatus("42", "42");
		assertTrue(actualStatus.getActiveStatus());
		assertEquals("Nickname", actualStatus.getNickname());
		assertEquals(10.0, actualStatus.getInterestRate().doubleValue());
		assertEquals("42", actualStatus.getId());
		assertEquals("01:01", actualStatus.getExpireDate().toLocalTime().toString());
		assertEquals("01:01", actualStatus.getCreateDate().toLocalTime().toString());
		assertEquals("42", actualStatus.getCardNumber());
		assertSame(currencyValue, actualStatus.getBalance());
		assertEquals(1, actualStatus.getBillCycleLength().intValue());
		CardTypeDTO cardType = actualStatus.getCardType();
		assertEquals("42", cardType.getId());
		assertEquals("The characteristics of someone or something", cardType.getDescription());
		assertEquals(10.0, cardType.getBaseInterestRate());
		assertEquals("https://example.org/example", cardType.getPreviewURL());
		assertEquals("Type Name", cardType.getTypeName());
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetStatus2() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname("Nickname");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(1);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById((String) any())).thenReturn(ofResult);
		CardDTO actualStatus = this.cardServiceImpl.getStatus("42", "42");
		assertTrue(actualStatus.getActiveStatus());
		assertEquals("Nickname", actualStatus.getNickname());
		assertEquals(10.0, actualStatus.getInterestRate().doubleValue());
		assertEquals("42", actualStatus.getId());
		assertEquals("01:01", actualStatus.getExpireDate().toLocalTime().toString());
		assertEquals("01:01", actualStatus.getCreateDate().toLocalTime().toString());
		assertEquals("42", actualStatus.getCardNumber());
		assertSame(currencyValue, actualStatus.getBalance());
		assertEquals(1, actualStatus.getBillCycleLength().intValue());
		CardTypeDTO cardType = actualStatus.getCardType();
		assertEquals("42", cardType.getId());
		assertEquals("The characteristics of someone or something", cardType.getDescription());
		assertEquals(10.0, cardType.getBaseInterestRate());
		assertEquals("https://example.org/example", cardType.getPreviewURL());
		assertEquals("Type Name", cardType.getTypeName());
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetStatus3() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(1);
		currencyValue.setCents(1);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName("Type Name");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole("Role");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("User Id");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname("Nickname");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(1);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);
		Optional<CardEntity> ofResult = Optional.<CardEntity>of(cardEntity);
		when(this.cardRepository.findById((String) any())).thenReturn(ofResult);
		assertNull(this.cardServiceImpl.getStatus("42", "42"));
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetStatus4() {
		when(this.cardRepository.findById((String) any())).thenReturn(Optional.<CardEntity>empty());
		assertNull(this.cardServiceImpl.getStatus("42", "42"));
		verify(this.cardRepository).findById((String) any());
	}

	@Test
	void testGetCardsByUser() {
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{"Sort By"}, "Search Criteria"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{","}, "Search Criteria"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{}, "Search Criteria"));
	}

	@Test
	void testGetCardsByUser2() {
		when(this.cardRepository.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardEntity>(new ArrayList<CardEntity>()));
		assertNull(this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{"Sort By", "Sort By"}, "Search Criteria"));
		verify(this.cardRepository).findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetCardsByUser3() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole(",");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname(",");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(0);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity);
		PageImpl<CardEntity> pageImpl = new PageImpl<CardEntity>(cardEntityList);
		when(this.cardRepository.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(1,
				this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{"Sort By", "Sort By"}, "Search Criteria")
						.toList()
						.size());
		verify(this.cardRepository).findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetCardsByUser4() {
		CurrencyValue currencyValue = new CurrencyValue();
		currencyValue.setDollars(0);
		currencyValue.setCents(0);
		currencyValue.setNegative(true);

		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		UserEntity userEntity = new UserEntity();
		userEntity.setLastName("Doe");
		userEntity.setPassword("iloveyou");
		userEntity.setEmail("jane.doe@example.org");
		userEntity.setRole(",");
		userEntity.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity.setUserId("42");
		userEntity.setUsername("janedoe");
		userEntity.setPhone("4105551212");
		userEntity.setFirstName("Jane");

		CardEntity cardEntity = new CardEntity();
		cardEntity.setBalance(currencyValue);
		cardEntity.setNickname(",");
		cardEntity.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setId("42");
		cardEntity.setCardType(cardTypeEntity);
		cardEntity.setBillCycleLength(0);
		cardEntity.setInterestRate(10.0);
		cardEntity.setUser(userEntity);
		cardEntity.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity.setCardNumber("42");
		cardEntity.setActiveStatus(true);

		CurrencyValue currencyValue1 = new CurrencyValue();
		currencyValue1.setDollars(0);
		currencyValue1.setCents(0);
		currencyValue1.setNegative(true);

		CardTypeEntity cardTypeEntity1 = new CardTypeEntity();
		cardTypeEntity1.setBaseInterestRate(10.0);
		cardTypeEntity1.setAvailable(true);
		cardTypeEntity1.setId("42");
		cardTypeEntity1.setTypeName(",");
		cardTypeEntity1.setDescription("The characteristics of someone or something");
		cardTypeEntity1.setPreviewURL("https://example.org/example");

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setLastName("Doe");
		userEntity1.setPassword("iloveyou");
		userEntity1.setEmail("jane.doe@example.org");
		userEntity1.setRole(",");
		userEntity1.setDateOfBirth(LocalDate.ofEpochDay(1L));
		userEntity1.setUserId("42");
		userEntity1.setUsername("janedoe");
		userEntity1.setPhone("4105551212");
		userEntity1.setFirstName("Jane");

		CardEntity cardEntity1 = new CardEntity();
		cardEntity1.setBalance(currencyValue1);
		cardEntity1.setNickname(",");
		cardEntity1.setExpireDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity1.setId("42");
		cardEntity1.setCardType(cardTypeEntity1);
		cardEntity1.setBillCycleLength(0);
		cardEntity1.setInterestRate(10.0);
		cardEntity1.setUser(userEntity1);
		cardEntity1.setCreateDate(LocalDateTime.of(1, 1, 1, 1, 1));
		cardEntity1.setCardNumber("42");
		cardEntity1.setActiveStatus(true);

		ArrayList<CardEntity> cardEntityList = new ArrayList<CardEntity>();
		cardEntityList.add(cardEntity1);
		cardEntityList.add(cardEntity);
		PageImpl<CardEntity> pageImpl = new PageImpl<CardEntity>(cardEntityList);
		when(this.cardRepository.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(2,
				this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{"Sort By", "Sort By"}, "Search Criteria")
						.toList()
						.size());
		verify(this.cardRepository).findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetCardsByUser5() {
		when(this.cardRepository.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any())).thenReturn(null);
		assertNull(this.cardServiceImpl.getCardsByUser("42", 10, 3, new String[]{"Sort By", "Sort By"}, "Search Criteria"));
		verify(this.cardRepository).findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetCardsByUser6() {
		when(this.cardRepository.findAllByUser_UserIdEqualsAndIdOrNicknameOrCardType_TypeNameContainsIgnoreCase(
				(String) any(), (String) any(), (String) any(), (String) any(),
				(org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardEntity>(new ArrayList<CardEntity>()));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getCardsByUser("42", -1, 3, new String[]{","}, "Search Criteria"));
	}

	@Test
	void testGetAvailableCardTypes() {
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{"Sort By"}, "Search"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{","}, "Search"));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{}, "Search"));
	}

	@Test
	void testGetAvailableCardTypes2() {
		when(this.cardTypeRepository.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardTypeEntity>(new ArrayList<CardTypeEntity>()));
		assertNull(this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{"Sort By", "Sort By"}, "Search"));
		verify(this.cardTypeRepository).findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAvailableCardTypes3() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		ArrayList<CardTypeEntity> cardTypeEntityList = new ArrayList<CardTypeEntity>();
		cardTypeEntityList.add(cardTypeEntity);
		PageImpl<CardTypeEntity> pageImpl = new PageImpl<CardTypeEntity>(cardTypeEntityList);
		when(this.cardTypeRepository.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(1,
				this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{"Sort By", "Sort By"}, "Search")
						.toList()
						.size());
		verify(this.cardTypeRepository).findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAvailableCardTypes4() {
		CardTypeEntity cardTypeEntity = new CardTypeEntity();
		cardTypeEntity.setBaseInterestRate(10.0);
		cardTypeEntity.setAvailable(true);
		cardTypeEntity.setId("42");
		cardTypeEntity.setTypeName(",");
		cardTypeEntity.setDescription("The characteristics of someone or something");
		cardTypeEntity.setPreviewURL("https://example.org/example");

		CardTypeEntity cardTypeEntity1 = new CardTypeEntity();
		cardTypeEntity1.setBaseInterestRate(10.0);
		cardTypeEntity1.setAvailable(true);
		cardTypeEntity1.setId("42");
		cardTypeEntity1.setTypeName(",");
		cardTypeEntity1.setDescription("The characteristics of someone or something");
		cardTypeEntity1.setPreviewURL("https://example.org/example");

		ArrayList<CardTypeEntity> cardTypeEntityList = new ArrayList<CardTypeEntity>();
		cardTypeEntityList.add(cardTypeEntity1);
		cardTypeEntityList.add(cardTypeEntity);
		PageImpl<CardTypeEntity> pageImpl = new PageImpl<CardTypeEntity>(cardTypeEntityList);
		when(this.cardTypeRepository.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
		assertEquals(2,
				this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{"Sort By", "Sort By"}, "Search")
						.toList()
						.size());
		verify(this.cardTypeRepository).findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any());
	}

	@Test
	void testGetAvailableCardTypes5() {
		when(this.cardTypeRepository.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any())).thenReturn(null);
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAvailableCardTypes(10, 3, new String[]{","}, "Search"));
	}

	@Test
	void testGetAvailableCardTypes6() {
		when(this.cardTypeRepository.findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase((String) any(),
				(String) any(), (org.springframework.data.domain.Pageable) any()))
				.thenReturn(new PageImpl<CardTypeEntity>(new ArrayList<CardTypeEntity>()));
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> this.cardServiceImpl.getAvailableCardTypes(-1, 3, new String[]{","}, "Search"));
	}
}

