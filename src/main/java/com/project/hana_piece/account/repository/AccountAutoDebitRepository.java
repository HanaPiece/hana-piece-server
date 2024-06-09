package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountAutoDebit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAutoDebitRepository extends JpaRepository<AccountAutoDebit, Long> {

    boolean existsByAccount_AccountIdAndTargetAccountId(Long accountId, Long targetAccountId);

    List<AccountAutoDebit> findByAutoDebitDay(Integer day);

    Optional<AccountAutoDebit> findByTargetAccountId(Long targetAccountId);

    Optional<AccountAutoDebit> findByAccount_AccountIdAndTargetAccountId(Long accountId, Long targetAccountId);
}
