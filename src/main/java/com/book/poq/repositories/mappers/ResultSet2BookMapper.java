package com.book.poq.repositories.mappers;

import com.book.poq.model.Book;
import com.book.poq.utils.BookFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSet2BookMapper<T extends Book> implements PoqRowMapper<T> {

    private ObjectMapper objectMapper;

    public ResultSet2BookMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T mapRow(ResultSet resultSet) throws SQLException {

        try {
            return objectMapper.readValue(
                    resultSet.getBytes("snapshot"),
                    BookFactory.getBookClazzByVersion(
                            resultSet.getString("version").trim()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException("Can`t read object " + e.getMessage());
        }
    }
}
