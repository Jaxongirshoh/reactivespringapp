package dev.wisespirit.reactivespringapp.dto;


import java.math.BigDecimal;

public record ProductDto( String name,String description,BigDecimal price ,int stock) {
}