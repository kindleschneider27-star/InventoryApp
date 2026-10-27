package com.example.inventoryapp.messaging;

import com.example.inventoryapp.config.MessagingProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemStatusEventProducer {
    private static final Logger logger = LoggerFactory.getLogger(ItemStatusEventProducer.class);
    private final KafkaTemplate<String, ItemStatusEvent> kafkaTemplate;
    private final MessagingProperties properties;

    public ItemStatusEventProducer(KafkaTemplate<String, ItemStatusEvent> kafkaTemplate, MessagingProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void send(ItemStatusEvent event) {
        kafkaTemplate.send(properties.getItemStatusTopic(),event.getItemId().toString(), event);
    }
}
