package dev.wisespirit.reactivespringapp.controller;

import dev.wisespirit.reactivespringapp.dto.category.CategoryCreateDto;
import dev.wisespirit.reactivespringapp.dto.category.CategoryDto;
import dev.wisespirit.reactivespringapp.dto.category.CategoryUpdateDto;
import dev.wisespirit.reactivespringapp.exception.BadRequestException;
import dev.wisespirit.reactivespringapp.exception.CategoryNotFoundException;
import dev.wisespirit.reactivespringapp.service.BaseCategoryService;
import dev.wisespirit.reactivespringapp.util.ApiResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final BaseCategoryService<CategoryDto, CategoryCreateDto, CategoryUpdateDto,Long> categoryService;

    public CategoryController(BaseCategoryService<CategoryDto, CategoryCreateDto, CategoryUpdateDto, Long> categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public Mono<ApiResponse<List<CategoryDto>>> getAllProducts(){
        return categoryService.findAllCategory()
                .collectList()
                .map(categoryDtos ->
                        categoryDtos.isEmpty()
                                ? new ApiResponse<List<CategoryDto>>(Collections.emptyList(), 200)
                                : new ApiResponse<>(categoryDtos, 204))
                .onErrorResume(CategoryNotFoundException.class, ex -> Mono.just(new ApiResponse<>(null, 404)));
    }

    @GetMapping("/{id}")
    public Mono<ApiResponse<CategoryDto>> getCategoryById(@PathVariable Long id){
        return categoryService.findById(id)
                .map(dto -> new ApiResponse<>(dto, 200))
                .onErrorResume(CategoryNotFoundException.class,
                        ex ->
                                Mono.just(new ApiResponse<>(null, 404, "product not found")));
    }

    @PostMapping()
    public Mono<ApiResponse<CategoryDto>> save(@RequestBody CategoryCreateDto dto){
        return categoryService.save(dto)
                .map(categoryDto -> new ApiResponse<>(categoryDto, 200))
                .onErrorResume(BadRequestException.class,
                        ex -> Mono.just(new ApiResponse<>(null, 400,"bad request : "+ex.getMessage())))
                .onErrorResume(er ->
                        Mono.just(new ApiResponse<>(null, "something went wrong " + er.getMessage())));
    }

    @PutMapping("/{id}")
    public Mono<ApiResponse<CategoryDto>> update(@RequestBody CategoryUpdateDto dto,
                                                 @PathVariable Long id){
        return categoryService.update(dto, id)
                .map(updated -> new ApiResponse<>(updated, 200))
                .onErrorResume(CategoryNotFoundException.class, err ->
                        Mono.just(new ApiResponse<>(null, 404, "category not found")))
                .onErrorResume(err ->
                        Mono.just(new ApiResponse<>(null, "something went wrong " + err.getMessage())));
    }
}
