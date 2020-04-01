package com.book.poq.controllers;


import com.book.poq.model.Book;
import com.book.poq.model.BookV0;
import com.book.poq.repositories.mappers.Book2BookV1Mapper;
import com.book.poq.services.BookService;
import com.book.poq.utils.BookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    protected BookService bookService;
    protected Book2BookV1Mapper mapper;

    public BookController(BookService bookService, Book2BookV1Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public Mono<BookV0> getBookById(@PathVariable int id){
        return bookService.getBookById(id, BookV0.class);
    }

    @GetMapping
    public Flux<BookV0> getAll(){
        return bookService.findAll(BookV0.class);
    }

    @PostMapping
    public Mono<Book> save(@RequestBody BookV0 bookV0){
        bookV0 = BookFactory.getFilledBookV0();
        return bookService.saveWithAdvert(bookV0);
    }


}
