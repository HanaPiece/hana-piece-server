package com.project.hana_piece.goal.dto;

public record ApartmentPricePredictRequest(String region, String apartmentNm, Long price, int area, String date) {

}