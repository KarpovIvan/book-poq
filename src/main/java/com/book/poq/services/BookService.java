package com.book.poq.services;

import com.book.poq.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    <T extends Book> Mono<T> getBookById(int id, Class<T> clazz);

    <T extends Book> Flux<T> findAll(Class<T> clazz);

    <T extends Book> Mono<T> saveWithAdvert(T book);

}
