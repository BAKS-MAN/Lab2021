package org.epam.api.requests;

import lombok.AllArgsConstructor;
import java.io.Serializable;

import static org.epam.util.DateTimeUtil.generateTimestampCustomPattern;

@AllArgsConstructor
public class DashboardRequest implements Serializable {
    String name;
    String description;
    boolean share;

    public static DashboardRequest getDashboardRequestObject() {
        String name = generateTimestampCustomPattern("ddMMyy_HHmmss");
        String description = "Dashboard created via API";
        return new DashboardRequest(name, description, false);
    }
}
