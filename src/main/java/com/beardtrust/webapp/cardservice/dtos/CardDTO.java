package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;

import lombok.Data;

@Data
public class CardDTO implements Serializable{
	
	private String cardId;
	private String userId;
	private CardTypeEntity cardType;
	private Double balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;

}
