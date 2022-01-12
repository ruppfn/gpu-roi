package ar.com.frupp.gpuroi.model;

import java.util.List;

public record Paginated<T>(Integer pageNumber, Long totalElements, Integer pageSize, List<T> content) {
}
