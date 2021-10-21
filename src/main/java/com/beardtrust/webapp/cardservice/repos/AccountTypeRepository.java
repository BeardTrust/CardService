package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, String> {
	AccountTypeEntity findByNameIs(String typeName);
}
