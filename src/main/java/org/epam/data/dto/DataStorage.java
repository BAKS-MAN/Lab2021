package org.epam.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataStorage {
    private static DataStorage instance;

    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    private String dashboardName;
}
