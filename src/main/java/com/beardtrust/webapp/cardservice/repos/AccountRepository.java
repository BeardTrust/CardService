/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.cardservice.repos;

import com.beardtrust.webapp.cardservice.entities.AccountEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.beardtrust.webapp.cardservice.entities.AccountTypeEntity;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Page<AccountEntity> findAllByUser_UserIdOrIdOrActiveStatusOrNicknameOrType_NameContainsIgnoreCase(String userId, String id, boolean activeStatus, String nickname, String typeName, Pageable pageable);
    
    Page<AccountEntity> findAllByBalanceOrInterestIsLike(CurrencyValue balance, Integer interest, Pageable pageable);
    
    Page<AccountEntity> findByCreateDate(LocalDateTime createDate, Pageable pageable);

    List<AccountEntity> findAllByUser_UserId(String userId);

    @Override
    Page<AccountEntity> findAll(Pageable page);

    /*
    *Find by Nickname
    *
     */
    AccountEntity findByNickname(String s);
}
