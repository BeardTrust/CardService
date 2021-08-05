package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardDTO implements Serializable{
	
	private String cardId;
	private String cardNumber;
	private String expiration;
	private String cardType;
	private String account_id;
	private String user_id;

}
