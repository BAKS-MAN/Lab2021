package org.epam.api.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.epam.data.dto.api.DashboardWidgetDTO;
import java.io.Serializable;

@AllArgsConstructor
@Builder
public class DashboardAddWidgetData implements Serializable {
    private boolean share;
    private int widgetId;
    private String widgetName;
    WidgetPosition widgetPosition;
    WidgetSize widgetSize;
    private String widgetType;

    @AllArgsConstructor
    static class WidgetPosition implements Serializable {
        private int positionX;
        private int positionY;
    }

    @AllArgsConstructor
    private static class WidgetSize implements Serializable {
        private int height;
        private int width;
    }

    public static DashboardAddWidgetData getDashboardAddWidgetData(DashboardWidgetDTO dashboardWidget) {
        WidgetPosition widgetPosition = new WidgetPosition(0, 0);
        WidgetSize widgetSize = new WidgetSize(7, 6);
        return DashboardAddWidgetData.builder()
                .share(false)
                .widgetId(Integer.parseInt(dashboardWidget.getWidgetId()))
                .widgetName(dashboardWidget.getWidgetName())
                .widgetType(dashboardWidget.getWidgetType())
                .widgetPosition(widgetPosition)
                .widgetSize(widgetSize)
                .build();
    }
}
