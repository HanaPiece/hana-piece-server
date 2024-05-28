package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts " +
        "WHERE account_type_cd IN('CHECKING','SALARY','LIFE','SAVING','SPARE') " +
        "AND user_id = :userId " +
        "ORDER BY account_type_cd ASC, account_id ASC", nativeQuery = true)
    List<Account> findCheckingAccount(Long userId);

    @Query(value = "SELECT * FROM accounts " +
        "WHERE account_type_cd IN('INSTALLMENT_SAVING') " +
        "AND user_id = :userId " +
        "ORDER BY account_type_cd ASC, account_id ASC", nativeQuery = true)
    List<Account> findSavingAccount(Long userId);
}