package com.book.poq.services.impl;

import com.book.poq.model.Book;
import com.book.poq.services.BookValidatorService;

public class BookValidatorServiceImpl implements BookValidatorService {

    @Override
    public boolean validate(Book book) {
        return !book.getAuthor().contains("Orwell");
    }
}
