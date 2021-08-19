package com.beardtrust.webapp.cardservice.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beardtrust.webapp.cardservice.entities.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String>{
	
	@Modifying
	@Query(value = "update cards set active_status=false where card_id=?1", nativeQuery=true)
	void deactivateById(String cardId);
	
	@Query(value = "select * from cards where active_status=true", nativeQuery=true)
	List<CardEntity> findAllActive();

}
