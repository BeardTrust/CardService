package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card_types")
public class CardTypeEntity {
	@Id
	private String id;
	private String typeName;
	private Double baseInterestRate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Double getBaseInterestRate() {
		return baseInterestRate;
	}

	public void setBaseInterestRate(Double baseInterestRate) {
		this.baseInterestRate = baseInterestRate;
	}

	@Override
	public String toString() {
		return typeName;
	}
}
