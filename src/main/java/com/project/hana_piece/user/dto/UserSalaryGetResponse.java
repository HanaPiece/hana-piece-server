package com.project.hana_piece.user.dto;

import com.project.hana_piece.user.domain.User;

public record UserSalaryGetResponse(
        Long userId,
        String email,
        String sex,
        Integer age,
        String qualificationTypeNm,
        String cityTypeNm,
        String nickname,
        Long salary,
        String accountNumber,
        Byte autoDebitDay) {

    public static UserSalaryGetResponse fromEntity(User user, String accountNumber, Byte autoDebitDay) {
        String cityTypeNm = user.getCityType() != null ? user.getCityType().getCityTypeNm() : null;
        return new UserSalaryGetResponse(
                user.getUserId(),
                user.getEmail(),
                user.getSex(),
                user.getAge(),
                user.getQualificationTypeCd(),
                cityTypeNm,
                user.getNickname(),
                user.getSalary(),
                accountNumber,
                autoDebitDay
        );
    }
}