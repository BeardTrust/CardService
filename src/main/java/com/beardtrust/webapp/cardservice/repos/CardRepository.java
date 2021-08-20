package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class gives access to the cards in the database.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {
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

	@Query(value = "select * from cards where active_status=true", nativeQuery = true)
	List<CardEntity> findAllActive();
}
