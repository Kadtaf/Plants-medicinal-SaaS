package com.plants.api.utils;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaginationUtils {

    /**
     * Convertit une liste en une page paginée.
     *
     * @param <T>      Type des éléments de la liste.
     * @param list     Liste à paginer.
     * @param pageable Paramètres de pagination.
     * @return Une page contenant les éléments paginés.
     */
    public <T> Page<T> paginateList(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        if (start > list.size()) {
            return new PageImpl<>(List.of(), pageable, list.size());
        }

        List<T> paginatedList = list.subList(start, end);
        return new PageImpl<>(paginatedList, pageable, list.size());
    }

    /**
     * Trie une liste selon un champ et une direction.
     *
     * @param <T>      Type des éléments de la liste.
     * @param list     Liste à trier.
     * @param sortField Champ selon lequel trier.
     * @param direction Direction du tri ("asc" ou "desc").
     * @return Liste triée.
     */
    public <T> List<T> sortList(List<T> list, String sortField, String direction) {
        try {
            java.util.Comparator<T> comparator = (o1, o2) -> {
                try {
                    Object field1 = o1.getClass().getDeclaredField(sortField).get(o1);
                    Object field2 = o2.getClass().getDeclaredField(sortField).get(o2);

                    if (field1 instanceof Comparable && field2 instanceof Comparable) {
                        if ("desc".equalsIgnoreCase(direction)) {
                            return ((Comparable) field2).compareTo(field1);
                        } else {
                            return ((Comparable) field1).compareTo(field2);
                        }
                    }
                    return 0;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to sort by field: " + sortField, e);
                }
            };

            return list.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to sort list: " + e.getMessage(), e);
        }
    }

    /**
     * Applique une pagination et un tri à une liste.
     *
     * @param <T>        Type des éléments de la liste.
     * @param list       Liste à paginer et trier.
     * @param pageable   Paramètres de pagination.
     * @param sortField  Champ selon lequel trier.
     * @param direction  Direction du tri ("asc" ou "desc").
     * @return Une page contenant les éléments paginés et triés.
     */
    public <T> Page<T> paginateAndSortList(List<T> list, Pageable pageable, String sortField, String direction) {
        List<T> sortedList = sortList(list, sortField, direction);
        return paginateList(sortedList, pageable);
    }
}
