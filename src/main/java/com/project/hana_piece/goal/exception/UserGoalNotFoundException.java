package com.project.hana_piece.goal.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class UserGoalNotFoundException extends EntityNotFoundException {

    public UserGoalNotFoundException() {
        super("Could not found UserGoal");
    }

    public UserGoalNotFoundException(Long id) {
        super("Could not found UserGoal"+id);
    }
}
