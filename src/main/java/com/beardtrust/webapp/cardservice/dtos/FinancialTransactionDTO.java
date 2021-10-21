package com.beardtrust.webapp.cardservice.dtos;

import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.entities.FinancialAsset;
import com.beardtrust.webapp.cardservice.entities.TransactionStatus;
import com.beardtrust.webapp.cardservice.entities.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * This is the Data Transfer Object for Financial Transactions.
 *
 * @author Matthew.Crowell@Smoothstack.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialTransactionDTO {
	private String id;
	private CurrencyValue transactionAmount;
	private TransactionStatus transactionStatus;
	private FinancialAsset source;
	private FinancialAsset target;
	private TransactionType transactionType;
	private String notes;
	private LocalDateTime statusTime;
}
