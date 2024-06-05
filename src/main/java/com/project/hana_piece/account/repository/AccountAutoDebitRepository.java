package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountAutoDebit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAutoDebitRepository extends JpaRepository<AccountAutoDebit, Long> {

    boolean existsByAccountAccountIdAndTargetAccountId(Long accountId, Long accountId1);

    List<AccountAutoDebit> findByAutoDebitDay(Integer day);
}
