package com.project.hana_piece.goal.controller;

import com.project.hana_piece.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserGoalController {

    private final UserService userService;
}
