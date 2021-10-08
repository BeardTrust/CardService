package com.beardtrust.webapp.cardservice.dtos;

import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.entities.FinancialAsset;
import com.beardtrust.webapp.cardservice.entities.TransactionStatus;
import com.beardtrust.webapp.cardservice.entities.TransactionType;
import lombok.Builder;
import lombok.Data;

/**
 * This is the Data Transfer Object for Financial Transactions.
 *
 * @author Matthew.Crowell@Smoothstack.com
 */
@Data
@Builder
public class FinancialTransactionDTO {
	String id;
	CurrencyValue amount;
	TransactionStatus transactionStatus;
	FinancialAsset source;
	FinancialAsset target;
	TransactionType transactionType;
	String notes;
}
