package com.book.poq.repositories.mappers;

import com.book.poq.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSet2BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Book(
                resultSet.getInt("id"),
                resultSet.getString("title").trim(),
                resultSet.getString("author").trim()
        );
    }
}
