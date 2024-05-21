package com.apifrontGroup.shopFrontApiRest.utils;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public static Map<String, Object> createPageResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent()); // Lista de elementos en la página actual
        response.put("currentPage", page.getNumber()); // Número de la página actual
        response.put("totalItems", page.getTotalElements()); // Total de elementos en todas las páginas
        response.put("totalPages", page.getTotalPages()); // Total de páginas
        return response;
    }
}
