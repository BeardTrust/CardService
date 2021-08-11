package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class gives access to the card types found in the database.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@Repository
public interface CardTypeRepository extends JpaRepository<CardTypeEntity, String> {
}
