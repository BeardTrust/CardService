package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
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
	private CardTypeDTO cardType;
	private CurrencyValue balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;
}
