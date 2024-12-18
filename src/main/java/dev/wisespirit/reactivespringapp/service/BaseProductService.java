package dev.wisespirit.reactivespringapp.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface BaseProductService<BT,CR,UP,ID extends Serializable> {
    Flux<BT> findALlProducts();
    Mono<BT> findById(ID id);
    Mono<BT> save(CR dto);
    Mono<BT> update(UP dto,ID id);
    Mono<Void> delete(ID id);
}
