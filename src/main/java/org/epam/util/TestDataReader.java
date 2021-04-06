package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataReader {
    private static final String TEST_DATA_PATH = "test_data/test.data";

    public static String getTestData(String propertyName) {
        Properties properties = new Properties();
        InputStream input = FileUtils.class.getClassLoader().getResourceAsStream(TEST_DATA_PATH);
        try {
            properties.load(input);
        } catch (IOException | NullPointerException e) {
            throw new NullPointerException("Unable to find resource: " + TEST_DATA_PATH);
        }
        return properties.getProperty(propertyName);
    }
}
