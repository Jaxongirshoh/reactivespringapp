package dev.wisespirit.reactivespringapp.repository;

import dev.wisespirit.reactivespringapp.entity.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryRepository extends ReactiveCrudRepository<Category,Long> {
}
