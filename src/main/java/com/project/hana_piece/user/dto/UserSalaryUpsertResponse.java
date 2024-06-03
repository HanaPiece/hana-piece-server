package com.project.hana_piece.user.dto;

import com.project.hana_piece.user.domain.User;

public record UserSalaryUpsertResponse(Long salary) {

    public static UserSalaryUpsertResponse fromEntity(User user){
        return new UserSalaryUpsertResponse(user.getSalary());
    }
}