package com.project.hana_piece.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    private final static DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String localDateTimeToYMDFormat(LocalDateTime localDateTime) {
        return localDateTime.format(ymdFormat);
    }
}
