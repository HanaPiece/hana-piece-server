package com.project.hana_piece.user.repository;

import com.project.hana_piece.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPassword(String password);
}
