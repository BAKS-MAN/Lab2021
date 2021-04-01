package org.epam.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TestDataReader {
    private static final String TEST_DATA_PATH = "test_data/test.data";

    @SneakyThrows
    public static String getTestData(String propertyName) {
        Properties properties = new Properties();
        InputStream input = FileUtils.class.getClassLoader().getResourceAsStream(TEST_DATA_PATH);
        if (input == null) {
            throw new IOException("Unable to find resource: " + TEST_DATA_PATH);
        }
        properties.load(input);
        return properties.getProperty(propertyName);
    }
}
