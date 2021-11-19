package org.epam.api.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import org.epam.api.responses.FilterData;
import org.epam.data.dto.api.DashboardWidgetDTO;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Builder
public class WidgetCreateRequest {
    private String widgetType;
    private ContentParameters contentParameters;
    private List<Filter> filters;
    private String name;
    private String description;
    private boolean share;
    private List<String> filterIds;

    @AllArgsConstructor
    @Builder
    static class ContentParameters {
        private String itemsCount;
        private List<String> contentFields;
        private Map<String, Object> widgetOptions;

        protected static ContentParameters getContentParametersObject() {
            Map<String, Object> widgetOptions = Map.of("viewMode", "bar");
            return ContentParameters.builder()
                    .contentFields(List.of("statistics$executions$total", "statistics$executions$passed"))
                    .itemsCount("50")
                    .widgetOptions(widgetOptions)
                    .build();
        }
    }

    @AllArgsConstructor
    static class Filter {
        private String value;
        private String name;
    }

    public static WidgetCreateRequest getCreateWidgetRequestObject(FilterData filterData,
            DashboardWidgetDTO widgetData) {
        String filterId = String.valueOf(filterData.getId());
        Filter filter = new Filter(filterId, filterData.getName());
        return WidgetCreateRequest.builder()
                .widgetType(widgetData.getWidgetType())
                .contentParameters(ContentParameters.getContentParametersObject())
                .filters(List.of(filter))
                .name(widgetData.getWidgetName())
                .description("")
                .filterIds(List.of(filterId))
                .share(true)
                .build();
    }
}
