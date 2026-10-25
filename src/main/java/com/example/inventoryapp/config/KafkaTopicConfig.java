package com.example.inventoryapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic itemStatusTopic(MessagingProperties properties) {
        return TopicBuilder.name(properties.getItemStatusTopic())
                .partitions(properties.getPartitions())
                .replicas(properties.getReplicas())
                .build();
    }
}
