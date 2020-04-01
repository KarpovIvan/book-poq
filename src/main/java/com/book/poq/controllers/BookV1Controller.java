package com.book.poq.controllers;

import com.book.poq.model.Book;
import com.book.poq.model.BookV1;
import com.book.poq.repositories.mappers.Book2BookV1Mapper;
import com.book.poq.services.BookService;
import com.book.poq.utils.BookFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/books")
public class BookV1Controller {

    BookService bookService;
    Book2BookV1Mapper mapper;

    public BookV1Controller(BookService bookService, Book2BookV1Mapper book2BookV1Mapper) {
        this.bookService = bookService;
        this.mapper = book2BookV1Mapper;
    }

    @GetMapping
    public Flux<BookV1> findAll(){
        return bookService.findAll(BookV1.class);
    }

    @PostMapping
    public Mono<Book> save(@RequestBody BookV1 bookV1){
        bookV1 = BookFactory.getFilledBookV1();
        return bookService.saveWithAdvert(bookV1);
    }

}
