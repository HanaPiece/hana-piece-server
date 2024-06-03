package com.project.hana_piece.product.dto;

import java.math.BigInteger;

public record EnrollProductRequest (
        Long productId,
        Long userGoalId,
        Integer contractPeriod,
        BigInteger initialAmount,
        Long autoDebitAmount,
        Integer autoDebitDay,
        String maturityDate,
        boolean autoRenewal
){

}
