package com.beardtrust.webapp.cardservice.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardUpdateModel {
	
	private String cardId;
	private String userId;
	private String cardType;
	private Double balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;
}
