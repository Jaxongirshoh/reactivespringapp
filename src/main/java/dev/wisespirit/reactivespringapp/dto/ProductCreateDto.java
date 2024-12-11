package dev.wisespirit.reactivespringapp.dto;

import java.math.BigDecimal;

public record ProductCreateDto(String name, String description, BigDecimal price , int stock) {
}
