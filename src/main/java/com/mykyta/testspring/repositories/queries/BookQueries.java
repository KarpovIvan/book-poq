package com.mykyta.testspring.repositories.queries;

public class BookQueries {
    public static final String GET_QUERY_BY_ID = "SELECT * FROM books WHERE id = ?";
    public static final String GET_QUERY_ALL = "SELECT * FROM books";
    public static final String INSERT_QUERY = "INSERT INTO books(title, author) VALUES (?, ?)";
}
