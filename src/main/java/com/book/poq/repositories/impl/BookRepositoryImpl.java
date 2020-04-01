package com.book.poq.repositories.impl;

import com.book.poq.model.Book;
import com.book.poq.repositories.BookRepository;
import com.book.poq.repositories.extractors.FluxSinkResultSetExtractor;
import com.book.poq.repositories.extractors.MonoSinkResultSetExtractor;
import com.book.poq.repositories.mappers.ResultSet2BookMapper;
import com.book.poq.repositories.queries.BookQueries;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import javax.sql.DataSource;

public class BookRepositoryImpl implements BookRepository {

    private JdbcTemplate jdbcTemplate;
    private Scheduler scheduler;
    private ObjectMapper objectMapper;

    public  BookRepositoryImpl(
            DataSource dataSource,
            Scheduler scheduler,
            ObjectMapper objectMapper
    ){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.scheduler = scheduler;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T extends Book> Mono<T> getBookById(int id, Class<T> clazz) {
        return Mono.create(monoSink ->
                jdbcTemplate.query(
                        BookQueries.GET_QUERY_BY_ID,
                        new Object[]{id},
                        new MonoSinkResultSetExtractor<>(new ResultSet2BookMapper<>(objectMapper), monoSink)
                ));
    }

    @Override
    public <T extends Book> Flux<T> findAll(Class<T> clazz){
        return Flux.<T>create(bookFluxSink ->
                    jdbcTemplate.query(
                            BookQueries.GET_QUERY_ALL,
                            new FluxSinkResultSetExtractor<>(new ResultSet2BookMapper<>(objectMapper), bookFluxSink))
                )
                .subscribeOn(scheduler);
    }

    @Override
    public <T extends Book> Mono<T> save(T book) {
        return Mono.create((sink) -> {
            try {
                jdbcTemplate.query(
                        String.format(BookQueries.INSERT_QUERY, objectMapper.writeValueAsString(book), book.getVersion()),
                        new MonoSinkResultSetExtractor<>(new ResultSet2BookMapper<>(objectMapper), sink)
                );
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });


    }
}
