package ar.com.frupp.gpuroi.model;

import java.util.List;

public record Paginated<T>(Integer pageNumber, Long totalElements, List<T> content) {
}
