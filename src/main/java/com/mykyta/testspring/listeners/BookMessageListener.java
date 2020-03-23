package com.mykyta.testspring.listeners;

import com.mykyta.testspring.model.Book;

public interface BookMessageListener {
    void process(Book bookMessage);
}
