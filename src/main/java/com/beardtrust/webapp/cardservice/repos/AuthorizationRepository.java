package com.beardtrust.webapp.cardservice.repos;

import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.data.repository.Repository;

import com.beardtrust.webapp.cardservice.entities.UserEntity;

@org.springframework.stereotype.Repository
public interface AuthorizationRepository extends Repository<UserEntity, String> {
	/**
	 * Find by user id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<UserEntity> findByUserId(String id);
}
