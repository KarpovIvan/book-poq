package com.book.poq.services;

import com.book.poq.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    Mono<Book> getBookById(int id);

    Flux<Book> findAll();

    void saveWithAdvert(Book book);

}
