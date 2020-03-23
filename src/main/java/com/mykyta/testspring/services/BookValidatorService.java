package com.mykyta.testspring.services;

import com.mykyta.testspring.model.Book;

public interface BookValidatorService {

    boolean validate(Book book);
}
