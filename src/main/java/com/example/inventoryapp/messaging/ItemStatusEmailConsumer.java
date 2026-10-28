package com.example.inventoryapp.messaging;

import com.example.inventoryapp.services.ItemService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ItemStatusEmailConsumer {
    ItemStatusEmailService emailService;



    public ItemStatusEmailConsumer(ItemStatusEmailService is) {
        emailService = is;
    }

    @KafkaListener(topics = "${KAFKA_ITEM_STATUS_TOPIC}", groupId = "${item.messaging.email-group}")

    public void receive(ItemStatusEvent event) {
        emailService.sendMessage(event);
    }

}
