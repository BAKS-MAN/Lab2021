package org.epam.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String getCurrentDateWithTime(String datePattern) {
        LocalDateTime date = LocalDateTime.now();
        return date.format(DateTimeFormatter.ofPattern(datePattern));
    }
}
