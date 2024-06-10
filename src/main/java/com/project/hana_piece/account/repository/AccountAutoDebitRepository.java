package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountAutoDebit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountAutoDebitRepository extends JpaRepository<AccountAutoDebit, Long> {

    boolean existsByAccountAccountIdAndTargetAccountId(Long accountId, Long accountId1);

    List<AccountAutoDebit> findByAutoDebitDay(Integer day);

    Optional<AccountAutoDebit> findByTargetAccountId(Long targetAccountId);

    Optional<AccountAutoDebit> findByAccountAccountIdAndTargetAccountId(Long accountId, Long targetAccountId);
}
