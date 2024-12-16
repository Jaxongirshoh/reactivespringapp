package dev.wisespirit.reactivespringapp.repository;

import dev.wisespirit.reactivespringapp.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product,Long> {
}
