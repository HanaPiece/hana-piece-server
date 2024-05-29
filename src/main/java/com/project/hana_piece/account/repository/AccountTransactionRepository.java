package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByAccountAccountId(Long accountId);
}
