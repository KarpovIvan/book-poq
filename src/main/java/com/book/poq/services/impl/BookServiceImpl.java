package com.book.poq.services.impl;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.model.Advert;
import com.book.poq.model.Book;
import com.book.poq.model.BookV0;
import com.book.poq.model.BookV1;
import com.book.poq.repositories.BookRepository;
import com.book.poq.repositories.mappers.Book2BookV1Mapper;
import com.book.poq.services.BookService;
import com.book.poq.services.BookValidatorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BookServiceImpl implements BookService {

    protected BookRepository bookRepository;
    protected AdvertAdapter advertAdapter;
    protected BookValidatorService bookValidatorService;
    protected Book2BookV1Mapper book2BookV1Mapper;

    public BookServiceImpl(BookRepository bookRepository, AdvertAdapter advertAdapter, BookValidatorService bookValidatorService, Book2BookV1Mapper book2BookV1Mapper) {
        this.bookRepository = bookRepository;
        this.advertAdapter = advertAdapter;
        this.bookValidatorService = bookValidatorService;
        this.book2BookV1Mapper = book2BookV1Mapper;
    }


    @Override
    public <T extends Book> Mono<T> getBookById(int id, Class<T> clazz) {
        return bookRepository.getBookById(id, clazz)
                .map(b -> mapToVersion(b, clazz))
                .filter(bookValidatorService::validate)
                .flatMap(b -> advertAdapter.getAdvert(0)
                        .defaultIfEmpty(new Advert())
                        .map(advert -> {
                                b.setAdvert(advert);
                                return b;
                        })
                );
    }


    @Override
    public <T extends Book> Flux<T> findAll(Class<T> clazz){
        return bookRepository.findAll(clazz)
                .map(b -> mapToVersion(b, clazz))
                .filter(bookValidatorService::validate);
    }

    @Override
    public <T extends Book> Mono<T> saveWithAdvert(T book) {
        return advertAdapter.getAdvert(0)
                .publishOn(Schedulers.elastic())
                .flatMap(advert -> {
                    book.setAdvert(advert);
                    return bookRepository.save(book);
                });
    }

    public <T extends Book, R extends Book> T mapToVersion(R book, Class<T> clazz) {
        if (book.getClass() == clazz)
            return (T) book;
        else if (book.getVersion().equals("v0"))
            return (T) book2BookV1Mapper.bookV0ToBookV1((BookV0) book);
        else if (book.getVersion().equals("v1"))
            return (T) book2BookV1Mapper.bookV1ToBookV0((BookV1) book);
        throw new ClassCastException("Cant map to any known class");
    }
}
