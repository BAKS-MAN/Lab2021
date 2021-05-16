package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilesDataReader {
    private static final String TEST_DATA_RESOURCES_PATH = "src/test/resources/test_data/";

    public static String readDataFromFile(String fileName) {
        String fileToReadPath = TEST_DATA_RESOURCES_PATH + fileName;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToReadPath))) {
            return bufferedReader.readLine();
        } catch (IOException | NullPointerException e) {
            throw new NullPointerException("Unable to find resource: " + fileToReadPath);
        }
    }
}
