package dev.wisespirit.reactivespringapp.dto.product;

import java.math.BigDecimal;

public record ProductUpdateDto(String name, String description, BigDecimal price , int stock) {
}
