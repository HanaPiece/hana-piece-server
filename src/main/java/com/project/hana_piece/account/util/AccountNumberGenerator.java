package com.project.hana_piece.account.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random RANDOM = new Random();
    private static final Integer PREFIX = 178;
    private static final int LENGTH_WITHOUT_PREFIX = 11;

    public static String generateAccountNumber() {
        StringBuilder result = new StringBuilder();

        result.append(PREFIX);
        for (int i = 0; i < LENGTH_WITHOUT_PREFIX; i++) {
            int digit = RANDOM.nextInt(10);
            result.append(digit);
        }

        return result.toString();
    }
}
