package com.book.poq.listeners.impl;

import com.book.poq.listeners.BookMessageListener;
import com.book.poq.model.Book;
import com.book.poq.services.BookService;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaBookMessageListenerImpl implements BookMessageListener {

    protected BookService bookService;

    public KafkaBookMessageListenerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @KafkaListener(topics = "BookTopic")
    public void process(Book bookMessage) {
        bookService.saveWithAdvert(bookMessage);
    }
}
