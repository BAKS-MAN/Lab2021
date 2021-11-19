package org.epam.data.dto.api;

import lombok.Builder;
import lombok.Data;

import static org.epam.util.TestDataUtil.getRandomNumberAsString;

@Data
@Builder
public class DashboardWidgetDTO {
    private String widgetId;
    private String widgetName;
    private String widgetType;
    private String description;

    public static DashboardWidgetDTO getDashboardWidgetObject() {
        String widgetName = "Widget_" + getRandomNumberAsString(3);
        String widgetType = "statisticTrend";
        return DashboardWidgetDTO.builder()
                .widgetName(widgetName)
                .widgetType(widgetType)
                .build();
    }
}
