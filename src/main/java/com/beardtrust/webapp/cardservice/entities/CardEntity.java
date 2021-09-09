package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * This class represents the card entities as found in the database.
 *
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Entity
@Table(name = "cards")
public class CardEntity extends FinancialAsset {
	private static final long serialVersionUID = 1790351438616081041L;

	private String nickname;
	private double interestRate;
	@ManyToOne
	private CardTypeEntity cardType;
	private LocalDateTime expireDate;
	private int billCycle;
	private String cardNumber;

	public CardEntity() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public CardTypeEntity getCardType() {
		return cardType;
	}

	public void setCardType(CardTypeEntity cardType) {
		this.cardType = cardType;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public int getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(int billCycle) {
		this.billCycle = billCycle;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CardEntity that = (CardEntity) o;
		return Double.compare(that.interestRate, interestRate) == 0 && billCycle == that.billCycle && Objects.equals(nickname, that.nickname) && Objects.equals(cardType, that.cardType) && Objects.equals(expireDate, that.expireDate) && Objects.equals(cardNumber, that.cardNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname, interestRate, cardType, expireDate, billCycle, cardNumber);
	}

	@Override
	public String toString() {
		return this.cardNumber;
	}
}