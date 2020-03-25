package com.book.poq.configurations;

import com.book.poq.listeners.BookMessageListener;
import com.book.poq.listeners.impl.KafkaBookMessageListenerImpl;
import com.book.poq.services.BookService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topic(){
        return new NewTopic("BookTopic", 2, (short)1);
    }

    @Bean
    public BookMessageListener kafkaBookMessageListener(BookService bookService){
        return new KafkaBookMessageListenerImpl(bookService);
    }
}
