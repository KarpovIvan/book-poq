package com.book.poq.services.impl;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.model.Book;
import com.book.poq.repositories.BookRepository;
import com.book.poq.services.BookService;
import com.book.poq.services.BookValidatorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BookServiceImpl implements BookService {

    protected BookRepository bookRepository;
    protected AdvertAdapter advertAdapter;
    protected BookValidatorService bookValidatorService;

    public BookServiceImpl(BookRepository bookRepository, AdvertAdapter advertAdapter, BookValidatorService bookValidatorService) {
        this.bookRepository = bookRepository;
        this.advertAdapter = advertAdapter;
        this.bookValidatorService = bookValidatorService;
    }

    @Override
    public Mono<Book> getBookById(int id) {
        return bookRepository.getBookById(id)
                .filter(bookValidatorService::validate)
                .flatMap(b -> advertAdapter.getAdvert(0)
                        .map(advert -> {
                                b.setAdvert(advert);
                                return b;
                        })
                );
    }


    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll()
                .filter(bookValidatorService::validate);
    }

    @Override
    public void saveWithAdvert(Book book) {
        advertAdapter.getAdvert(0)
                .publishOn(Schedulers.elastic())
                .doOnSuccess(advert -> {
                    book.setAdvert(advert);
                    bookRepository.save(book);
                })
                .block();
    }
}
