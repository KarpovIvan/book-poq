package com.mykyta.testspring.repositories;

import com.mykyta.testspring.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {

    Mono<Book> getBookById(int id);

    Flux<Book> findAll();

    void save(Book book);
}
