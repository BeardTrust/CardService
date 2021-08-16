package com.beardtrust.webapp.cardservice.dtos;

import lombok.Data;

@Data
public class CardTypeDTO {
	private String id;
	private double baseInterestRate;
	private String name;
}
