package com.book.poq.configurations;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.configurations.scheduler.PoqBookDatabaseSchedulerProperties;
import com.book.poq.repositories.BookRepository;
import com.book.poq.repositories.impl.BookRepositoryImpl;
import com.book.poq.repositories.mappers.ResultSet2BookMapper;
import com.book.poq.services.BookService;
import com.book.poq.services.BookValidatorService;
import com.book.poq.services.impl.BookServiceImpl;
import com.book.poq.services.impl.BookValidatorServiceImpl;
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
    public ResultSet2BookMapper resultSet2BookMapper(){
        return new ResultSet2BookMapper();
    }

    @Bean
    public BookValidatorService bookValidatorService(){
        return new BookValidatorServiceImpl();
    }

    @Bean
    public BookRepository bookRepository(DataSource dataSource, ResultSet2BookMapper resultSet2BookMapper, Scheduler scheduler){
        return new BookRepositoryImpl(dataSource, resultSet2BookMapper, scheduler);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository, AdvertAdapter advertAdapter, BookValidatorService bookValidatorService){
        return new BookServiceImpl(
                        bookRepository,
                        advertAdapter,
                        bookValidatorService
        );
    }

}
