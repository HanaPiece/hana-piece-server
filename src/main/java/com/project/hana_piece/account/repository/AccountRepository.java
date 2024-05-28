package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByAccountTypeCdAndUserUserId(Long userId);
}
