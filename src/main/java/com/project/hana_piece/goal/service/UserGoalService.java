package com.project.hana_piece.goal.service;

import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserRepository userRepository;
}
