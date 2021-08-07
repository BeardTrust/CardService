package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;
import lombok.Data;

@Data
public class CardDTO implements Serializable{
	
	private String cardId;
	private String userId;
	private String cardType;
	private Float balance;
	private String cardNumber;
	private Float interestRate;
	private String createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private String expireDate;

}
