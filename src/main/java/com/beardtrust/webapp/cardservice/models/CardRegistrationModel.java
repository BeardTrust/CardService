package com.beardtrust.webapp.cardservice.models;

import java.time.LocalDate;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CardRegistrationModel {
	
	private String user;
	@NotBlank(message = "A card type must be provided")
	private String cardType;
	@NotBlank(message = "A balance must be provided")
	@NotBlank(message = "An interest rate must be provided")
	private Float interestRate;
	@NotBlank(message = "A nickname must be provided")
	private String nickname;
	@NotBlank(message = "A bill cycle length must be provided")
	private Integer billCycleLength;
	
	
}
