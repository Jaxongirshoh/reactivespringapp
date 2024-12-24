package dev.wisespirit.reactivespringapp.dto.product;


import java.math.BigDecimal;

public record ProductDto( Long id,String name,String description,BigDecimal price ,int stock) {
}