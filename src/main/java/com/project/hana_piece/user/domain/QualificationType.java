package com.project.hana_piece.user.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 근무 직종
 * SELF_EMP 자영업자
 * ENTERPRISE 기업체
 * PROFESSION 전문직
 * OFFICE 직장인
 */
public enum QualificationType  {

    SELF_EMP("SELF_EMP"),
    ENTERPRISE("ENTERPRISE"),
    PROFESSION("PROFESSION"),
    OFFICE("OFFICE");
    private final String property;

    QualificationType(String property) {
        this.property = property;
    }
}
