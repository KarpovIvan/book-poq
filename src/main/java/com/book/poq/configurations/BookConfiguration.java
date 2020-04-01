package com.book.poq.configurations;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.configurations.scheduler.PoqBookDatabaseSchedulerProperties;
import com.book.poq.repositories.BookRepository;
import com.book.poq.repositories.impl.BookRepositoryImpl;
import com.book.poq.repositories.mappers.Book2BookV1Mapper;
import com.book.poq.services.BookService;
import com.book.poq.services.BookValidatorService;
import com.book.poq.services.impl.BookServiceImpl;
import com.book.poq.services.impl.BookValidatorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.sql.DataSource;


@Configuration
@EnableConfigurationProperties(PoqBookDatabaseSchedulerProperties.class)
public class BookConfiguration {

    @Bean
    public Scheduler databaseScheduler(PoqBookDatabaseSchedulerProperties properties){
        return Schedulers.newBoundedElastic(
                properties.getThreads(),
                properties.getQueue(),
                properties.getName()
        );
    }

    @Bean
    public BookValidatorService bookValidatorService(){
        return new BookValidatorServiceImpl();
    }

    @Bean
    public BookRepository bookRepository(DataSource dataSource, Scheduler scheduler, ObjectMapper objectMapper){
        return new BookRepositoryImpl(dataSource, scheduler, objectMapper);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository, AdvertAdapter advertAdapter, BookValidatorService bookValidatorService, Book2BookV1Mapper book2BookV1Mapper){
        return new BookServiceImpl(
                        bookRepository,
                        advertAdapter,
                        bookValidatorService,
                        book2BookV1Mapper
        );
    }

}
