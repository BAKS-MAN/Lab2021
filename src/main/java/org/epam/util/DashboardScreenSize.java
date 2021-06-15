package org.epam.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DashboardScreenSize {
    MINIMAL_SCREEN_WIDTH(0.2),
    HALF_SCREEN_WIDTH(0.5),
    FULL_SCREEN_WIDTH(1.0);
    private final double value;
}
