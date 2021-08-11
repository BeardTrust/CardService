package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;
import lombok.Data;

/**
 * This class provides an object for the transfer of card data
 * outside of the cards service.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
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
