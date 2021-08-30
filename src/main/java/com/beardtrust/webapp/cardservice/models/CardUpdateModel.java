package com.beardtrust.webapp.cardservice.models;

import java.time.LocalDate;

import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardUpdateModel {
	
	private String cardId;
	private String userId;
	private String cardType;
	private CurrencyValue balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;
}
