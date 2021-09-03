package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * The interface Authorization repository.
 *
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@org.springframework.stereotype.Repository
public interface AuthorizationRepository extends Repository<UserEntity, String> {
	/**
	 * Find by user id optional.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<UserEntity> findById(String id);
}
