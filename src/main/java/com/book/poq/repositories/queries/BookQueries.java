package com.book.poq.repositories.queries;

public class BookQueries {
    public static final String GET_QUERY_BY_ID = "SELECT * FROM books_v2 WHERE id = ?";
    public static final String GET_QUERY_ALL = "SELECT * FROM books_v2";
    public static final String INSERT_QUERY = "SELECT * FROM save_book_with_version('%s', '%s');";
}
