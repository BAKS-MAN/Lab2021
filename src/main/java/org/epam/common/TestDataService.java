package org.epam.common;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataService {
    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        String commonProperties = "properties/common.properties";

        try (InputStream input = FileUtils.class.getClassLoader().getResourceAsStream(commonProperties)) {
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
