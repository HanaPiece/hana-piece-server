package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.Apartment;

public record ApartmentGetResponse(Long apartmentId, String apartmentNm, Long apartmentPrice, Long regionCd, String regionNm, Long exclusiveArea) {

    public static ApartmentGetResponse fromEntity(Apartment apartment) {
        return new ApartmentGetResponse(
                apartment.getApartmentId(),
                apartment.getApartmentNm(),
                apartment.getApartmentPrice(),
                apartment.getRegionCd(),
                apartment.getRegionNm(),
                apartment.getExclusiveArea()
        );
    }
}
