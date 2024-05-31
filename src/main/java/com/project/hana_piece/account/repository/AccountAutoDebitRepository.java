package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountAutoDebit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAutoDebitRepository extends JpaRepository<AccountAutoDebit, Long> {

    boolean existsByAccountAccountIdAndTargetAccountId(Long accountId, Long accountId1);
}
