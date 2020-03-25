package com.book.poq.configurations.kafka;

import com.book.poq.listeners.impl.KafkaBookMessageListener;
import com.book.poq.services.BookService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableConfigurationProperties(PoqBookTopicProperties.class)
public class PoqKafkaConfiguration {

    @Bean
    public NewTopic bookTopic(PoqBookTopicProperties properties){
        return TopicBuilder
                .name(properties.getName())
                .partitions(properties.getPartitions())
                .replicas(properties.getReplicas())
                .build();
    }

    @Bean
    public KafkaBookMessageListener kafkaBookMessageListener(BookService bookService){
        return new KafkaBookMessageListener(bookService);
    }
}
