package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

<<<<<<< HEAD
/**
 * This class represents the card types as found in the database.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
=======
>>>>>>> BTLLC-Feature-79/81/82/84
@Entity
@Table(name="card_types")
public class CardTypeEntity {
	@Id
	private String id;
<<<<<<< HEAD
	private String previewURL;
	private String typeName;
	private String description;
	private Double baseInterestRate;
	private Boolean isAvailable;
=======
	private String typeName;
	private Double baseInterestRate;

	public CardTypeEntity(String id, String typeName, Double baseInterestRate) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.baseInterestRate = baseInterestRate;
	}
>>>>>>> BTLLC-Feature-79/81/82/84

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

<<<<<<< HEAD
	public String getPreviewURL() {
		return previewURL;
	}

	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}

=======
>>>>>>> BTLLC-Feature-79/81/82/84
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
<<<<<<< HEAD

	public Boolean getAvailable() {
		return isAvailable;
	}

	public void setAvailable(Boolean available) {
		isAvailable = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return typeName;
	}
=======
>>>>>>> BTLLC-Feature-79/81/82/84
}
