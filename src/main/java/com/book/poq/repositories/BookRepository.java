package com.book.poq.repositories;

import com.book.poq.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    <T extends Book> Mono<T> getBookById(int id, Class<T> clazz);

    <T extends Book> Flux<T> findAll(Class<T> clazz);

    <T extends Book> Mono<T> save(T book);
}
