package com.beardtrust.webapp.cardservice.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CardSignUpRequestModel implements Serializable {
	private static final long serialVersionUID = 3198220360350432839L;
	private String cardType;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String role;
	private Date dateOfBirth;
	private String nickname;
}