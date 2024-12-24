package dev.wisespirit.reactivespringapp.service.impl;

import dev.wisespirit.reactivespringapp.dto.category.CategoryCreateDto;
import dev.wisespirit.reactivespringapp.dto.category.CategoryDto;
import dev.wisespirit.reactivespringapp.dto.category.CategoryUpdateDto;
import dev.wisespirit.reactivespringapp.entity.Category;
import dev.wisespirit.reactivespringapp.exception.BadRequestException;
import dev.wisespirit.reactivespringapp.exception.CategoryNotFoundException;
import dev.wisespirit.reactivespringapp.repository.CategoryRepository;
import dev.wisespirit.reactivespringapp.service.BaseCategoryService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostgresCategoryServiceImpl implements BaseCategoryService<CategoryDto, CategoryCreateDto, CategoryUpdateDto,Long> {

    private final CategoryRepository categoryRepository;

    public PostgresCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Mono<CategoryDto> save(CategoryCreateDto dto) {
        return categoryRepository.save(new Category(dto.name(), dto.parentId()))
                .map(savedEntity ->
                        new CategoryDto(savedEntity.getId(),
                                savedEntity.getName(),
                                savedEntity.getParentId()))
                .onErrorResume(err->Mono.error(new BadRequestException("something went wrong "+err.getMessage())));
    }

    @Override
    public Mono<CategoryDto> update(CategoryUpdateDto dto, Long aLong) {
        return categoryRepository.findById(aLong)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException("category not found id :"+aLong)))
                .flatMap(entity->{
                    entity.setName(dto.name());
                    entity.setParentId(dto.parentId());
                    return categoryRepository.save(entity);
                })
                .map(entity->new CategoryDto(entity.getId(),
                        entity.getName(),
                        entity.getParentId()));

    }

    @Override
    public Mono<Void> deleteById(Long aLong) {
        return categoryRepository.findById(aLong)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException("Category not found id :" + aLong)))
                .then(categoryRepository.deleteById(aLong))
                .onErrorMap(ex -> new RuntimeException("Error deleting category with id: " + aLong, ex));
    }

    @Override
    public Mono<CategoryDto> findById(Long aLong) {
        return categoryRepository.findById(aLong)
                .switchIfEmpty(Mono.error(new CategoryNotFoundException("category not found id :" + aLong)))
                .map(entity -> new CategoryDto(entity.getId(),
                        entity.getName(),
                        entity.getParentId()));
    }

    @Override
    public Flux<CategoryDto> findAllCategory() {
        return categoryRepository.findAll()
                .map(entity -> new CategoryDto(
                                entity.getId(),
                                entity.getName(),
                                entity.getParentId()))
                .switchIfEmpty(Flux.error(new CategoryNotFoundException("category empty")));
    }
}
