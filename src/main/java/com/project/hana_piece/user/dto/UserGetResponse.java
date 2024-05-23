package com.project.hana_piece.user.dto;

import com.project.hana_piece.user.domain.User;

public record UserGetResponse(Long userId, String email, String sex, Integer age, String qualificationTypeNm, String cityTypeNm, String nickname, Long salary) {

    public static UserGetResponse fromEntity(User user){
        String cityTypeNm = user.getCityType() != null ? user.getCityType().getCityTypeNm(): null;

        return new UserGetResponse(user.getUserId(), user.getEmail(), user.getSex(), user.getAge(), user.getQualificationTypeCd().toString(), cityTypeNm, user.getNickname(), user.getSalary());
    }
}
