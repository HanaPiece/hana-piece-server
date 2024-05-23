package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
