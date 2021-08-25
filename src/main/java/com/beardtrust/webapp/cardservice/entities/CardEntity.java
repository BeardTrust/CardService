package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


/**
 * This class represents the card entities as found in the database.
 *
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Entity
@Table(name = "cards")
public class CardEntity implements Serializable {
	private static final long serialVersionUID = 2520473012614795491L;

	@Id
	@Column(unique = true)
	private String cardId;
	private String userId;
	@ManyToOne
	private CardTypeEntity cardType;
	@Embedded
	private Balance balance;
	@Column(unique = true)
	private String cardNumber;
	private Double interestRate;
	private LocalDate createDate;
	private String nickname;
	private Integer billCycleLength;
	private Boolean activeStatus;
	private LocalDate expireDate;


	public CardEntity() {
		super();
	}

	public CardEntity(String cardId, String userId, CardTypeEntity cardType, Balance balance, String cardNumber,
					  Double interestRate, LocalDate createDate, String nickname, Integer billCycleLength, Boolean activeStatus,
					  LocalDate expireDate) {
		super();
		this.cardId = cardId;
		this.userId = userId;
		this.cardType = cardType;
		this.balance = balance;
		this.cardNumber = cardNumber;
		this.interestRate = interestRate;
		this.createDate = createDate;
		this.nickname = nickname;
		this.billCycleLength = billCycleLength;
		this.activeStatus = activeStatus;
		this.expireDate = expireDate;
	}

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

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
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