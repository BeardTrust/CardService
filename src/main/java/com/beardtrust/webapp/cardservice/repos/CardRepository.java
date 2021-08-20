package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * This class gives access to the cards in the database.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {
	Page<CardEntity> findAll(Pageable page);

	Page<CardEntity> findAllByCardIdOrUserIdOrCardNumberOrCardType_TypeNameOrNicknameContainsIgnoreCase(String cardId,
																										String userId,
																										String CardNumber,
																										String cardTypeName,
																										String nickname,
																										Pageable page);

	Page<CardEntity> findAllByBalanceOrInterestRateIsLike(Double balance, Double interestRate, Pageable page);

	Page<CardEntity> findAllByCreateDateOrExpireDateIsLike(LocalDate createDate, LocalDate expireDate, Pageable page);

	/**
	 * This method accepts a user id as an argument and returns the list of
	 * all cards associated with that user id as found in the database.
	 *
	 * @param userId String the user id to search for
	 * @return List<CardEntity> the list of cards associated with the user id
	 */
	List<CardEntity> findAllByUserId(String userId);

	@Modifying
	@Query(value = "update cards set active_status=false where card_id=?1", nativeQuery = true)
	void deactivateById(String cardId);

	Page<CardEntity> findAllByActiveStatusTrue(Pageable page);
}
