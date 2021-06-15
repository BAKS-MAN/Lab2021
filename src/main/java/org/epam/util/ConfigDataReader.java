package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.epam.util.ConfigurationConstants.ENVIRONMENT;
import static org.epam.util.ConfigurationConstants.LOCAL_ENVIRONMENT;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigDataReader {

    public static String getConfigData(String propertyName) {
        return getProperty(System.getProperty(ENVIRONMENT, LOCAL_ENVIRONMENT), propertyName);
    }

    private static String getProperty(String environment, String propertyName) {
        Properties properties = new Properties();
        String resourcePath = String.format("properties/%s.properties", environment);
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath);
        try {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            throw new NullPointerException("Unable to find resource: " + resourcePath);
        }
        return properties.getProperty(propertyName);
    }
}
