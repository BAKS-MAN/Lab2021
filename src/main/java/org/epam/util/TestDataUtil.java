package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import java.util.Random;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {
    public static String getRandomNumberAsString(int numberOfDigits) {
        StringBuilder number = new StringBuilder();
        IntStream.range(0, numberOfDigits).forEach(i -> number.append(RandomUtils.nextInt(10)));
        return number.toString();
    }

    public static int getRandomNumberAsInteger(int numberOfDigits) {
        int m = (int) Math.pow(10, numberOfDigits - 1D);
        return m + new Random().nextInt(9 * m);
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
