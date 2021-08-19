package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class gives access to the card types found in the database.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Repository
public interface CardTypeRepository extends JpaRepository<CardTypeEntity, String> {
	Page<CardTypeEntity> findAllByIsAvailable(Boolean availability, Pageable page);

	Page<CardTypeEntity> findAllByIsAvailableTrueAndTypeNameOrDescriptionContainsIgnoreCase(String typeName,
																							String description,
																							Pageable pageable);

	Page<CardTypeEntity> findAllByIsAvailableTrueAndBaseInterestRateIsLike(Double baseInterestRate, Pageable pageable);
}
