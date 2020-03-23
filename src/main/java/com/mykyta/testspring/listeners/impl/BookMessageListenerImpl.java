package com.mykyta.testspring.listeners.impl;

import com.mykyta.testspring.listeners.BookMessageListener;
import com.mykyta.testspring.model.Book;
import com.mykyta.testspring.services.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class BookMessageListenerImpl implements BookMessageListener {

    protected BookService bookService;

    public BookMessageListenerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @RabbitListener(queues = "bookQueue")
    public void process(Book book) {
        bookService.saveWithAdvert(book);
    }
}
