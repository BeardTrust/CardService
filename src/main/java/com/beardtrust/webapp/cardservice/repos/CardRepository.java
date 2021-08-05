package com.beardtrust.webapp.cardservice.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.beardtrust.webapp.cardservice.entities.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String>{

}
