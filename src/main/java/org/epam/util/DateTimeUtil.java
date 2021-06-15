package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtil {
    public static String getCurrentDateWithTime(String datePattern) {
        LocalDateTime date = LocalDateTime.now();
        return date.format(DateTimeFormatter.ofPattern(datePattern));
    }

    public static String generateTimestamp() {
        return getCurrentDateWithTime("yyyy-MM-dd'T'HH:mm:ss");
    }
}
