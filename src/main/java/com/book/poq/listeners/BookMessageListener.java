package com.book.poq.listeners;

import com.book.poq.model.Book;

public interface BookMessageListener {
    void process(Book bookMessage);
}
