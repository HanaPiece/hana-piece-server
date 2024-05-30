package com.project.hana_piece.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    private final static DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("dd");

    public static String localDateTimeToYMDFormat(LocalDateTime localDateTime) {
        return localDateTime.format(ymdFormat);
    }

    public static Integer localDateTimeToDayFormat(LocalDateTime localDateTime) {
        String day = localDateTime.format(dayFormat);
        return Integer.parseInt(day);
    }
}
