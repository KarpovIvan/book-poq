package com.book.poq.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface PoqRowMapper<T> {
    T mapRow(ResultSet resultSet) throws SQLException;
}
