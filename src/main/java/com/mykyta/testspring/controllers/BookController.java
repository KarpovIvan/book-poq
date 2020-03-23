package com.mykyta.testspring.controllers;


import com.mykyta.testspring.model.Book;
import com.mykyta.testspring.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/books")
public class BookController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    protected BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public Flux<Book> getAll(){
        return bookService.findAll();
    }

}
