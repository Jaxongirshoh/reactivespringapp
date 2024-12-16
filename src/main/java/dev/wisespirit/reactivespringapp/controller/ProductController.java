package dev.wisespirit.reactivespringapp.controller;

import dev.wisespirit.reactivespringapp.dto.ProductDto;
import dev.wisespirit.reactivespringapp.exception.ProductNotFoundException;
import dev.wisespirit.reactivespringapp.service.impl.PostgresqlProductServiceImpl;
import dev.wisespirit.reactivespringapp.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

    private final PostgresqlProductServiceImpl productService;

    public ProductController(PostgresqlProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public Mono<ApiResponse<List<ProductDto>>> getALlProducts(){
        return productService.findALlProducts()
                .collectList()
                .map(productDtos ->
                        productDtos.isEmpty()
                                ? new ApiResponse<>(Collections.emptyList(), 204)
                                : new ApiResponse<>(productDtos, 200))
                .onErrorResume(Exception.class,
                        cause ->
                                Mono.just(new ApiResponse(null, 500, "Internal server error")));
    }
}
