package com.mykyta.testspring.configurations;

import com.mykyta.testspring.adapters.AdvertAdapter;
import com.mykyta.testspring.adapters.impl.AdvertAdapterImpl;
import com.mykyta.testspring.repositories.mappers.ResultSet2BookMapper;
import com.mykyta.testspring.repositories.BookRepository;
import com.mykyta.testspring.repositories.impl.BookRepositoryImpl;
import com.mykyta.testspring.services.BookService;
import com.mykyta.testspring.services.BookValidatorService;
import com.mykyta.testspring.services.impl.BookServiceImpl;
import com.mykyta.testspring.services.impl.BookValidatorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.sql.DataSource;


@Configuration
public class BookConfiguration {

    @Bean
    public Scheduler databaseScheduler(){
        return Schedulers.newBoundedElastic(5, Integer.MAX_VALUE, "database");
    }

    @Bean
    public AdvertAdapter advertAdapter(WebClient.Builder builder){
        return new AdvertAdapterImpl(builder);
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
