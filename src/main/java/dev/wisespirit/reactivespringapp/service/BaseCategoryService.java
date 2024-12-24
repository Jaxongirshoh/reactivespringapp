package dev.wisespirit.reactivespringapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface BaseCategoryService<BT,CR,UP,ID extends Serializable> {
    Mono<BT> save(CR dto);
    Mono<BT> update(UP dto,ID id);
    Mono<Void> deleteById(ID id);
    Mono<BT> findById(ID id);
    Flux<BT> findAllCategory();
}
