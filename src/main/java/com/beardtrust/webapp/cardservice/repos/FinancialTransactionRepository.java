package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.dtos.FinancialTransactionDTO;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.entities.FinancialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.stream.DoubleStream;

/**
 * This is the repository for Financial Transactions.
 *
 * @author Matthew.Crowell@Smoothstack.com
 */
@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, String> {
	/**
	 * This method returns a Page of financial transactions where the source's id
	 * matches the sourceUserId argument or the target's id matches the
	 * targetUserId argument.
	 *
	 * @param sourceUserId the source's userId
	 * @param targetUserId the target's userId
	 * @param page         the pageable object defining the page request
	 * @return the requested page
	 */
	Page<FinancialTransaction> findAllBySource_IdOrTarget_IdIs(String sourceUserId, String targetUserId,
															   Pageable page);

	/**
	 * This method returns a Page of financial transactions where the status date is
	 * between the specified local date times.
	 *
	 * @param startDate the earliest local date time to look for
	 * @param endDate   the latest local date time to look for
	 * @param page      the pageable object defining the page request
	 * @return the requested page
	 */
	Page<FinancialTransaction> findAllByStatusTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable page);

	/**
	 * This method returns a Page of financial transactions where the amount is equal
	 * to the currencyValue argument.
	 *
	 * @param currencyValue the amount to look for
	 * @param page          the pageable object defining the page request
	 * @return the requested page
	 */
	Page<FinancialTransaction> findAllByTransactionAmountEquals(CurrencyValue currencyValue, Pageable page);

	/**
	 * This method returns a Page of financial transactions matching the given status,
	 * source, target, or notes.
	 *
	 * @param status   the status to match
	 * @param sourceId the source id to match
	 * @param targetId the target id to match
	 * @param notes    the notes to match
	 * @param page     the Pageable object defining the page request
	 * @return the requested page
	 */
	Page<FinancialTransaction> findAllByTransactionStatus_StatusNameOrSource_IdOrTarget_IdEqualsOrNotesContainsIgnoreCase(String status,
																														  String sourceId,
																														  String targetId,
																														  String notes,
																														  Pageable page);

	Page<FinancialTransaction> findBySource_IdAndTransactionAmountOrTarget_IdAndTransactionAmount(String cardId, CurrencyValue searchAmount, String cardId1, CurrencyValue searchAmount1, Pageable page);

	Page<FinancialTransaction> findBySource_IdAndTransactionStatus_StatusNameOrSource_IdAndNotesOrTarget_IdAndTransactionStatus_StatusNameOrTarget_IdAndNotes(String cardId, String search, String cardId1, String search1, String cardId2, String search2, String cardId3, String search3, Pageable page);

	Page<FinancialTransaction> findBySource_IdAndStatusTimeBetweenOrTarget_IdAndStatusTimeBetween(String cardId, LocalDateTime startTime, LocalDateTime endTime, String cardId1, LocalDateTime startTime1, LocalDateTime endTime1, Pageable page);
}
