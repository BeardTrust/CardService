package com.beardtrust.webapp.cardservice.models;

import java.time.LocalDateTime;

import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardUpdateModel {
	
	private String id;
	private String user;
	private String cardType;
	private CurrencyValue balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDateTime createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDateTime expireDate;
}
