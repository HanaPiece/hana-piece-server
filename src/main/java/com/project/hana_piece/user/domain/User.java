package com.project.hana_piece.user.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import com.project.hana_piece.common.domain.CityType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", length = 300, nullable = false)
    private String email;

    @Column(name = "sex", length = 1)
    private String sex;  //M W

    @Column(name = "age")
    private Integer age;

    @Column(name = "qualification_type_cd")
    private String qualificationTypeCd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_type_cd")
    private CityType cityType;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "salary_day")
    private Integer salaryDay;
}