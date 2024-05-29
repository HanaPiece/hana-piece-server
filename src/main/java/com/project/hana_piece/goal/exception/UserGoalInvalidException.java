package com.project.hana_piece.goal.exception;

import com.project.hana_piece.common.exception.ValueInvalidException;

public class UserGoalInvalidException extends ValueInvalidException {

    public UserGoalInvalidException() {
        super("Invalid UserGoal");
    }

    public UserGoalInvalidException(Long id) {
        super("Invalid UserGoal"+id);
    }
}
