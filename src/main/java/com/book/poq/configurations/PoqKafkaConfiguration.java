package com.book.poq.configurations;

import com.book.poq.listeners.impl.KafkaBookMessageListener;
import com.book.poq.services.BookService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PoqKafkaConfiguration {

    @Bean
    public NewTopic bookTopic(){
        return TopicBuilder
                .name("BookTopic")
                .partitions(2)
                .replicas((short)1)
                .build();
    }

    @Bean
    public KafkaBookMessageListener kafkaBookMessageListener(BookService bookService){
        return new KafkaBookMessageListener(bookService);
    }
}
