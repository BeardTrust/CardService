package com.beardtrust.webapp.cardservice.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class CardEntity implements Serializable{
	
	@Id
	@Column(unique=true)
	private String cardId;
	//@OneToMany
	private String userId;
	private String cardType;
	private Float balance;
	@Column(unique=true)
	private String cardNumber;
	private Float interestRate;
	private String createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private String expireDate;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Float getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getBillCycleLength() {
		return billCycleLength;
	}
	public void setBillCycleLength(Integer billCycleLength) {
		this.billCycleLength = billCycleLength;
	}
	public Boolean getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + ", userId=" + userId + ", cardType=" + cardType + ", balance=" + balance
				+ ", cardNumber=" + cardNumber + ", interestRate=" + interestRate + ", createDate=" + createDate
				+ ", nickname=" + nickname + ", billCycleLength=" + billCycleLength + ", activeStatus=" + activeStatus
				+ ", expireDate=" + expireDate + "]";
	}

	
	
}
