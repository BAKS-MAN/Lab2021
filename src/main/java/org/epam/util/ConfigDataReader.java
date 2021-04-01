package org.epam.util;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.epam.util.ConfigurationConstants.ENVIRONMENT;


public class ConfigDataReader {

    public static String getConfigData(String propertyName) {
        return getProperty(System.getProperty(ENVIRONMENT), propertyName);
    }

    @SneakyThrows
    private static String getProperty(String environment, String propertyName) {
        Properties properties = new Properties();
        String resourcePath = String.format("properties/%s.properties", environment);
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Unable to find resource: " + resourcePath);
        }
        properties.load(inputStream);
        return properties.getProperty(propertyName);
    }
}
