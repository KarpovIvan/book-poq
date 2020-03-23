package com.mykyta.testspring.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.awt.print.Book;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topic(){
        return new NewTopic("Test", 2, (short)1);
    }

    @KafkaListener(topics = "Test")
    public void kafkaListener(Book s){
        System.out.println(s);
    }
}
