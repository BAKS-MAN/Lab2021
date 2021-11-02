package org.epam.api.requests;

import lombok.AllArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
public class DashboardAddWidgetRequest implements Serializable {
    private DashboardAddWidgetData addWidget;
}
