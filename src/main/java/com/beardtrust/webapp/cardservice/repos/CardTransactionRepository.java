package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardTransaction;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * This is the repository for Card Transactions.
 *
 * @author Matthew.Crowell@Smoothstack.com
 */
@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, String> {
	/**
	 * This method returns a Page of card transactions where the source's id
	 * matches the sourceUserId argument or the target's id matches the
	 * targetUserId argument.
	 *
	 * @param sourceUserId the source's userId
	 * @param targetUserId the target's userId
	 * @param page         the pageable object defining the page request
	 * @return the requested page
	 */
	Page<CardTransaction> findAllBySource_IdOrTarget_IdIs(String sourceUserId, String targetUserId,
														  Pageable page);

	/**
	 * This method returns a Page of card transactions where the status date is
	 * between the specified local date times.
	 *
	 * @param startDate the earliest local date time to look for
	 * @param endDate   the latest local date time to look for
	 * @param page      the pageable object defining the page request
	 * @return the requested page
	 */
	Page<CardTransaction> findAllByStatusTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable page);

	/**
	 * This method returns a Page of card transactions where the amount is equal
	 * to the currencyValue argument.
	 *
	 * @param currencyValue the amount to look for
	 * @param page          the pageable object defining the page request
	 * @return the requested page
	 */
	Page<CardTransaction> findAllByTransactionAmountEquals(CurrencyValue currencyValue, Pageable page);

	/**
	 * This method returns a Page of card transactions matching the given status,
	 * source, target, or notes.
	 *
	 * @param status   the status to match
	 * @param sourceId the source id to match
	 * @param targetId the target id to match
	 * @param notes    the notes to match
	 * @param page     the Pageable object defining the page request
	 * @return the requested page
	 */
	Page<CardTransaction> findAllByTransactionStatus_StatusNameOrSource_IdOrTarget_IdEqualsOrNotesContainsIgnoreCase(String status,
																													 String sourceId,
																													 String targetId,
																													 String notes,
																													 Pageable page);

	/**
	 * This method returns a page of card transactions associated with the given
	 * card identifier that match the given status date.
	 *
	 * @param source_id   the identifier of the associated card
	 * @param statusTime  the date of the transaction
	 * @param statusTime2 the date of the transaction
	 * @param target_id   the identifier of the associated card
	 * @param statusTime3 the date of the transaction
	 * @param statusTime4 the date of the transaction
	 * @param pageable    page request
	 * @return a page of card transactions matching the given card id and date
	 */
	Page<CardTransaction> findBySource_IdAndStatusTimeBetweenOrTarget_IdAndStatusTimeBetween(String source_id,
																							 LocalDateTime statusTime,
																							 LocalDateTime statusTime2,
																							 String target_id,
																							 LocalDateTime statusTime3,
																							 LocalDateTime statusTime4,
																							 Pageable pageable);

	/**
	 * This method returns a page of card transactions associated with the given
	 * card identifier where notes or name of the transaction status match the
	 * given string.
	 *
	 * @param source_id                     the identifier of the associated card
	 * @param transactionStatus_statusName  the given status name
	 * @param source_id2                    the identifier of the associated card
	 * @param notes                         the given notes
	 * @param target_id                     the identifier of the associated card
	 * @param transactionStatus_statusName2 the given status name
	 * @param target_id2                    the identifier of the associated card
	 * @param notes2                        the given notes
	 * @param page                          page request
	 * @return a page of card transactions matching the given card id and search string
	 */
	Page<CardTransaction> findBySource_IdAndTransactionStatus_StatusNameOrSource_IdAndNotesOrTarget_IdAndTransactionStatus_StatusNameOrTarget_IdAndNotes(String source_id, String transactionStatus_statusName, String source_id2, String notes, String target_id, String transactionStatus_statusName2, String target_id2, String notes2, Pageable page);

	/**
	 * This method returns a page of card transactions associated with the given
	 * card identifier where the amount matches the amount given.
	 *
	 * @param source_id          the identifier of the associated card
	 * @param transactionAmount  the amount to match
	 * @param target_id          the identifier of the associated card
	 * @param transactionAmount2 the amount to match
	 * @param pageable           page request
	 * @return a page of card transactions matching the given card id and amount
	 */
	Page<CardTransaction> findBySource_IdAndTransactionAmountOrTarget_IdAndTransactionAmount(String source_id,
																							  CurrencyValue transactionAmount, String target_id, CurrencyValue transactionAmount2, Pageable pageable);
}