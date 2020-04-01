package com.book.poq.repositories.extractors;

import com.book.poq.repositories.mappers.PoqRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import reactor.core.publisher.FluxSink;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FluxSinkResultSetExtractor<T> implements ResultSetExtractor<T> {
    private PoqRowMapper<T> rowMapper;
    private FluxSink<T> fluxSink;

    public FluxSinkResultSetExtractor(PoqRowMapper<T> rowMapper, FluxSink<T> fluxSink) {
        this.rowMapper = rowMapper;
        this.fluxSink = fluxSink;
    }

    @Override
    public T extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        while (resultSet.next()) {
            fluxSink.next(rowMapper.mapRow(resultSet));
        }
        fluxSink.complete();
        return null;
    }
}