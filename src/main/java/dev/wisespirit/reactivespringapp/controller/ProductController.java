package dev.wisespirit.reactivespringapp.controller;

import dev.wisespirit.reactivespringapp.dto.ProductCreateDto;
import dev.wisespirit.reactivespringapp.dto.ProductDto;
import dev.wisespirit.reactivespringapp.dto.ProductUpdateDto;
import dev.wisespirit.reactivespringapp.exception.BadRequestException;
import dev.wisespirit.reactivespringapp.exception.ProductNotFoundException;
import dev.wisespirit.reactivespringapp.service.BaseProductService;
import dev.wisespirit.reactivespringapp.service.impl.PostgresqlProductServiceImpl;
import dev.wisespirit.reactivespringapp.util.ApiResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

    private final BaseProductService<ProductDto,ProductCreateDto, ProductUpdateDto,Long> productService;

    public ProductController(PostgresqlProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public Mono<ApiResponse<List<ProductDto>>> getALlProducts(){
        return productService.findALlProducts()
                .collectList()
                .map(productDtos ->
                        productDtos.isEmpty()
                                ? new ApiResponse<List<ProductDto>>(Collections.emptyList(), 204)
                                : new ApiResponse<>(productDtos, 200))
                .onErrorResume(Exception.class,
                        cause -> Mono.just(new ApiResponse<>(null, 500, "Internal server error")));
    }

    @GetMapping("/{id}")
    public Mono<ApiResponse<ProductDto>> getById(@PathVariable Long id){
        return productService.findById(id)
                .map(productDto -> new ApiResponse<>(productDto, 200))
                .onErrorResume(ProductNotFoundException.class,
                        cause ->
                                Mono.just(new ApiResponse<>(null, 404, "product not found")));
    }

    @PostMapping
    public Mono<ApiResponse<ProductDto>> save(@RequestBody ProductCreateDto dto){
        return productService.save(dto)
                .map(savedEntity ->
                        new ApiResponse<>(new ProductDto(
                                savedEntity.id(),
                                savedEntity.name(),
                                savedEntity.description(),
                                savedEntity.price(),
                                savedEntity.stock()), 201))
                .onErrorResume(BadRequestException.class,
                        cause->Mono.just(new ApiResponse<>(null,400,"Bad request")))
                .onErrorResume(cause->
                        Mono.just(new ApiResponse<>(null,"something went wrong : "+cause.getMessage())));
    }

    @DeleteMapping("/{id}")
    public Mono<ApiResponse<Object>> deleteById(@PathVariable Long id){
        return productService.delete(id)
                .then(Mono.just(new ApiResponse<>(null,"success")))
                .onErrorResume(ProductNotFoundException.class,
                        cause -> Mono.just(new ApiResponse<>(null, 404, "product not found")))
                .onErrorResume(cause ->
                        Mono.just(new ApiResponse<>(null, "something went wrong : " + cause.getMessage())));
    }

    @PutMapping("/{id}")
    public Mono<ApiResponse<ProductDto>> update(@RequestBody ProductUpdateDto dto,
                                                @PathVariable Long id){
        return productService.update(dto, id)
                .map(productDto -> new ApiResponse<>(productDto, "updated"))
                .onErrorResume(cause ->
                        Mono.just(new ApiResponse<>(null, "something went wrong : " + cause.getMessage())));
    }
}
