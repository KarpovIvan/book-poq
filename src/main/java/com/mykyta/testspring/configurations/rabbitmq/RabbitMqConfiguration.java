package com.mykyta.testspring.configurations.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mykyta.testspring.listeners.BookMessageListener;
import com.mykyta.testspring.listeners.impl.BookMessageListenerImpl;
import com.mykyta.testspring.repositories.BookRepository;
import com.mykyta.testspring.services.BookService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue bookQueue() {
        return QueueBuilder.durable("bookQueue").build();
    }

    @Bean
    public Exchange bookExchange(){
        return ExchangeBuilder.fanoutExchange("bookExchange").build();
    }

    @Bean
    public Binding bookBinding() {
        return BindingBuilder.bind(bookQueue()).to(bookExchange()).with("").noargs();
    }

    @Bean
    public BookMessageListener bookMessageListener(BookService bookService){
        return new BookMessageListenerImpl(bookService);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
