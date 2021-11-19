package org.epam.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data
public class FilterData {
    private String owner;
    private boolean share;
    private int id;
    private String name;
    private String type;
    private List<Condition> conditions;
    private List<Order> orders;

    @AllArgsConstructor
    private static class Condition {
        private String filteringField;
        private String condition;
        private String value;
    }

    @AllArgsConstructor
    private static class Order {
        private String sortingColumn;
        private boolean isAsc;
    }
}
