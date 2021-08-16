package com.beardtrust.webapp.cardservice.dtos;

import lombok.Data;

@Data
public class CardTypeDTO {
	private String id;
	private String previewURL;
	private double baseInterestRate;
	private String typeName;
	private String description;
}
