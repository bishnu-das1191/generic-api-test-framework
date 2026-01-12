package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    private DateTimeUtil() {
        // Private constructor to prevent instantiation
    }

    public static String getTimeWithDaysAgo(int daysAgo) {
        return Instant.now().minus(daysAgo, ChronoUnit.DAYS)
                .toString()
                .substring(0,10);
    }
}
