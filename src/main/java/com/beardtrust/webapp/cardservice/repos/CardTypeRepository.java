package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.CardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardTypeEntity, String> {
}
