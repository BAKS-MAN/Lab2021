package org.epam.util;

import com.github.invictum.reportportal.LogsPreset;
import com.github.invictum.reportportal.ReportIntegrationConfig;

public class ReportportalConfig {
    private static ReportportalConfig instance;

    public static ReportportalConfig getInstance() {
        if (instance == null) {
            instance = new ReportportalConfig();
        }
        return instance;
    }

    private ReportportalConfig() {
        ReportIntegrationConfig configuration = ReportIntegrationConfig.get();
        configuration.usePreset(LogsPreset.DEFAULT);
    }
}
