package com.book.poq.repositories.impl;

import com.book.poq.model.Book;
import com.book.poq.repositories.mappers.ResultSet2BookMapper;
import com.book.poq.repositories.BookRepository;
import com.book.poq.repositories.queries.BookQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryImpl implements BookRepository {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private ResultSet2BookMapper resultSet2BookMapper;
    private Scheduler scheduler;

    public  BookRepositoryImpl(DataSource dataSource, ResultSet2BookMapper resultSet2BookMapper, Scheduler scheduler){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.resultSet2BookMapper = resultSet2BookMapper;
        this.scheduler = scheduler;
    }

    @Override
    public Mono<Book> getBookById(int id) {
        return Mono.create(monoSink ->
                jdbcTemplate.query(BookQueries.GET_QUERY_BY_ID, new Object[]{id}, new ResultSetExtractor<Book>() {
                    @Override
                    public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        if(resultSet.next()) {
                            monoSink.success(resultSet2BookMapper.mapRow(resultSet, resultSet.getRow()));
                        } else {
                            monoSink.success();
                        }
                        return null;
                    }
                })
        );
    }

    @Override
    public Flux<Book> findAll(){
        LOGGER.info("in");
        return Flux.<Book>create(bookFluxSink ->
                    jdbcTemplate.query(BookQueries.GET_QUERY_ALL, new ResultSetExtractor<Book>(){
                        @Override
                        public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                            while (resultSet.next()) {
                                bookFluxSink.next(resultSet2BookMapper.mapRow(resultSet, resultSet.getRow()));
                            }
                            bookFluxSink.complete();
                            return null;
                        }
                    })
                )
                .subscribeOn(scheduler);
    }

    @Override
    public void save(Book book) {
        LOGGER.info("Get: " + book);
        int inserted = jdbcTemplate.update(BookQueries.INSERT_QUERY, book.getTitle(), book.getAuthor());
        LOGGER.info("Inserted: " + inserted);

    }
}
