package com.project.hana_piece.user.repository;

import com.project.hana_piece.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPassword(String password);
}
