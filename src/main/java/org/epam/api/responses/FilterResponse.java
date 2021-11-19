package org.epam.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class FilterResponse {
    private List<FilterData> content;
    private Page page;

    @AllArgsConstructor
    private static class Page {
        private int number;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
