package dev.wisespirit.reactivespringapp.service.impl;

import dev.wisespirit.reactivespringapp.dto.product.ProductCreateDto;
import dev.wisespirit.reactivespringapp.dto.product.ProductDto;
import dev.wisespirit.reactivespringapp.dto.product.ProductUpdateDto;
import dev.wisespirit.reactivespringapp.entity.Product;
import dev.wisespirit.reactivespringapp.exception.BadRequestException;
import dev.wisespirit.reactivespringapp.exception.ProductNotFoundException;
import dev.wisespirit.reactivespringapp.repository.ProductRepository;
import dev.wisespirit.reactivespringapp.service.BaseProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * This class demonstrate that Service class for Postgresql database<br/>
 * This class use R2DBC/WEBFLUX for persistence operations<br/>
 * Entities which are used in this class configured for SQL databases<br/>
 * Look {@link Product} class for how it configured
 *
 * @author wisespirit
 */
@Service
public class PostgresqlProductServiceImpl implements BaseProductService<ProductDto, ProductCreateDto, ProductUpdateDto, Long> {

    private final ProductRepository productRepository;

    public PostgresqlProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductDto> findALlProducts() {
        return productRepository.findAll().map(entity -> new ProductDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock()))
                .switchIfEmpty(Flux.error(new ProductNotFoundException("Empty list of products")));
    }

    @Override
    public Mono<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(entity -> new ProductDto(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock()))
                .switchIfEmpty(Mono.error(new ProductNotFoundException("product not found"+id)));
    }

    @Override
    public Mono<ProductDto> save(ProductCreateDto dto) {
        return productRepository.save(new Product(null,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stock()))
                .map(saved -> new ProductDto(saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock()))
                .switchIfEmpty(Mono.error(new BadRequestException("something went wrong")));
    }

    @Override
    public Mono<ProductDto> update(ProductUpdateDto dto, Long id) {
        return productRepository.findById(id)
                .flatMap(entity -> {
                    entity.setName(dto.name());
                    entity.setDescription(dto.description());
                    entity.setPrice(dto.price());
                    entity.setStock(dto.stock());
                    return productRepository.save(entity);
                }).map(updatedEntity->new ProductDto(
                        updatedEntity.getId(),
                        updatedEntity.getName(),
                        updatedEntity.getDescription(),
                        updatedEntity.getPrice(),
                        updatedEntity.getStock()
                )).switchIfEmpty(Mono.error(new ProductNotFoundException("failing during update")));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("product not found : "+id)))
                .then(productRepository.deleteById(id));
    }

    public Mono<Boolean> existById(Long id){
        return productRepository.existsById(id);
    }
}
