package com.beardtrust.webapp.cardservice.repos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.beardtrust.webapp.cardservice.entities.CardEntity;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String>{
	List<CardEntity> findAllByUserId(String userId);
}
