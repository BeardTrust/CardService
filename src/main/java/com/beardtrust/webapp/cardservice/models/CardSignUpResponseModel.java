package com.beardtrust.webapp.cardservice.models;

import lombok.Data;

/**
 * This class models the response to a card sign up request.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Data
public class CardSignUpResponseModel {
	private String cardId;
}
