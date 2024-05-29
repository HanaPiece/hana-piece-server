package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.Car;

public record CarGetResponse(String carNm, Long carPrice) {

    public static CarGetResponse fromEntity(Car car) {
        return new CarGetResponse(
                car.getCarNm(),
                car.getCarPrice()
        );
    }
}
