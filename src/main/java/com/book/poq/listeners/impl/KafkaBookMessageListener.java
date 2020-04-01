package com.book.poq.listeners.impl;

import com.book.poq.model.BookV0;
import com.book.poq.services.BookService;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaBookMessageListener{

    protected BookService bookService;

    public KafkaBookMessageListener(BookService bookService) {
        this.bookService = bookService;
    }

    @KafkaListener(topics = "BookTopic")
    public void process(BookV0 bookMessage) {
        bookService.saveWithAdvert(bookMessage);
    }
}
