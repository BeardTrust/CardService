package com.beardtrust.webapp.cardservice.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class CardEntity implements Serializable{
	
	@Id
	@Column(unique=true)
	private String cardId;
	private String userId;
	@ManyToOne
	private CardTypeEntity cardType;
	private Double balance;
	@Column(unique=true)
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;
	
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
	public CardTypeEntity getCardType() {
		return cardType;
	}
	public void setCardType(CardTypeEntity cardType) {
		this.cardType = cardType;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
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
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
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
