package org.epam.util;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
    private static final String PROPERTY_PATH = "properties/common.properties";
    private static final String TEST_DATA_PATH = "test_data/test.data";
    private static final String TEST_DATA_TYPE = "test";
    private static final String CONFIG_DATA_TYPE = "config";

    public static String getConfigData(String propertyName) {
        return getProperty(CONFIG_DATA_TYPE, propertyName);
    }

    public static String getTestData(String dataName) {
        return getProperty(TEST_DATA_TYPE, dataName);
    }

    private static String getProperty(String dataType, String propertyName) {
        Properties properties = new Properties();
        String resourcePath = dataType.equals(CONFIG_DATA_TYPE) ? PROPERTY_PATH : TEST_DATA_PATH;

        try (InputStream input = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                System.out.println("Unable to find property: " + propertyName);
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }
}
