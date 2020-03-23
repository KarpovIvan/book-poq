package com.mykyta.testspring.services.impl;

import com.mykyta.testspring.model.Book;
import com.mykyta.testspring.services.BookValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookValidatorServiceImpl implements BookValidatorService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BookValidatorServiceImpl.class);

    @Override
    public boolean validate(Book book) {
        boolean valid = !book.getAuthor().contains("Orwell");
        if (!valid)
            LOGGER.info(String.format("Book \"%s\" of %s CENSORED", book.getTitle(), book.getAuthor()));
        return valid;
    }
}
