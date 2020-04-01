package com.book.poq.repositories.extractors;

import com.book.poq.repositories.mappers.PoqRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import reactor.core.publisher.MonoSink;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonoSinkResultSetExtractor<T> implements ResultSetExtractor<T> {

    private PoqRowMapper<T> rowMapper;
    private MonoSink<T> monoSink;

    public MonoSinkResultSetExtractor(PoqRowMapper<T> rowMapper, MonoSink<T> monoSink) {
        this.rowMapper = rowMapper;
        this.monoSink = monoSink;
    }

    @Override
    public T extractData(ResultSet rs) throws SQLException, DataAccessException {
        if(rs.next()) {
            monoSink.success(rowMapper.mapRow(rs));
        } else {
            monoSink.success();
        }
        return null;
    }
}
