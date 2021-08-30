package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CurrencyValue implements Serializable {
	private static final long serialVersionUID = -7883135732977736303L;

	@NotNull(message = "Negative value indicator cannot be null")
	private boolean isNegative;
	private int dollars;
	@Min(value = -99, message = "Cents cannot exceed the value of a dollar")
	@Max(value = 99, message = "Cents cannot exceed the value of a dollar")
	private int cents;

	public CurrencyValue() {
		this(false, 0, 0);
	}

	public CurrencyValue(int dollars, int cents) {
		this(dollars < 0 || cents < 0, dollars, cents);
	}

	public CurrencyValue(CurrencyValue amount) {
		this(amount.isNegative, amount.dollars, amount.cents);
	}

	public CurrencyValue(boolean isNegative, int dollarsAmount, int centsAmount) {
		if ((dollarsAmount < 0 || centsAmount < 0)) {
			isNegative = true;
		}

		if (isNegative) {
			dollarsAmount = Math.abs(dollarsAmount) * -1;
			centsAmount = Math.abs(centsAmount) * -1;
		}

		if (centsAmount < -99 || centsAmount > 99) {
			dollarsAmount += centsAmount / 100;
			centsAmount = centsAmount % 100;
		}

		if (centsAmount < 0 && dollarsAmount > 0) {
			dollarsAmount--;
			centsAmount += 100;
		} else if (dollarsAmount < 0 && centsAmount > 0) {
			dollarsAmount++;
			centsAmount -= 100;
		}

		this.setCents(centsAmount);
		this.setDollars(dollarsAmount);

	}

	public boolean isNegative() {
		return isNegative;
	}

	public void setNegative(boolean negative) {
		isNegative = negative;
	}

	public int getDollars() {
		return this.dollars;
	}

	public void setDollars(int dollars) {
		if (dollars < 0) {
			this.setNegative(true);
		}

		this.dollars = Math.abs(dollars);
	}

	public int getCents() {
		return this.cents;
	}

	public void setCents(int cents) {
		if (cents < 0) {
			this.setNegative(true);
		}

		this.cents = Math.abs(cents);
	}

	public void add(int dollarsAmount, int centsAmount) {
		this.add(new CurrencyValue(dollarsAmount, centsAmount));
	}

	public void add(CurrencyValue amount) {
		int currentDollarsValue = this.isNegative ? this.getDollars() * -1 : this.getDollars();
		int currentCentsValue = this.isNegative ? this.getCents() * -1 : this.getCents();

		currentCentsValue += amount.isNegative ? amount.getCents() * -1 : amount.getCents();
		currentDollarsValue += amount.isNegative ? amount.getDollars() * -1 : amount.getDollars();

		if (currentCentsValue < -99 || currentCentsValue > 99) {
			currentDollarsValue += currentCentsValue / 100;
			currentCentsValue = currentCentsValue % 100;
		}

		if (currentCentsValue < 0 && currentDollarsValue > 0) {
			currentDollarsValue--;
			currentCentsValue += 100;
		} else if (currentDollarsValue < 0 && currentCentsValue > 0) {
			currentDollarsValue++;
			currentCentsValue -= 100;
		}

		this.setNegative(currentCentsValue < 0 || currentDollarsValue < 0);

		this.setCents(currentCentsValue);
		this.setDollars(currentDollarsValue);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CurrencyValue that = (CurrencyValue) o;
		return isNegative == that.isNegative && dollars == that.dollars && cents == that.cents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isNegative, dollars, cents);
	}

	@Override
	public String toString() {
		return (((this.isNegative) ? "-" : "") + "$" + this.dollars + "." + ((this.cents < 10) ? "0" : "") + this.cents);
	}
}
