package com.beardtrust.webapp.cardservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class CardEntity implements Serializable{
	
	@Id
	@Column(unique=true)
	private String cardId;
	@Column(unique=true)
	private String cardNumber;
	private String expiration;
	private String cardType;
	@OneToOne
	private String account_id;
	@OneToMany
	private String user_id;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + ", cardNumber=" + cardNumber + ", expiration=" + expiration
				+ ", cardType=" + cardType + ", account_id=" + account_id + ", user_id=" + user_id + "]";
	}
	
}
