package com.beardtrust.webapp.cardservice.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents transactions related to a BeardTrust card.
 *
 * @author Matthew.Crowell@Smoothstack.com
 */
@Entity
@Table(name = "card_transactions")
public class CardTransaction extends FinancialTransaction{
	private static final long serialVersionUID = 1889513568968226661L;
}