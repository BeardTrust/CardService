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
	@OneToMany
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

	@OneToOne
	private String account_id;

	
}
