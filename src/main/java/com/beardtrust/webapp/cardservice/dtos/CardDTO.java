package com.beardtrust.webapp.cardservice.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	private static final long serialVersionUID = 4819495263235490837L;
	private String id;
	private CardTypeDTO cardType;
	private CurrencyValue balance;
	private String cardNumber;
	private Double interestRate;
	private LocalDateTime createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDateTime expireDate;
}
