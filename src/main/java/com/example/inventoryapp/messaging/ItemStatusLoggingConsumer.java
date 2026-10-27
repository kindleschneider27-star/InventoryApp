package com.example.inventoryapp.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ItemStatusLoggingConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ItemStatusLoggingConsumer.class);

    @KafkaListener(topics = "${KAFKA_ITEM_STATUS_TOPIC}", groupId ="${KAFKA_LOGGING_GROUP}")
    public void loggingReceive(ItemStatusEvent event) {
        logger.info("Received ItemStatusEvent: {} - {}", event.getItemName(), event.getItemInventory());
    }
}
