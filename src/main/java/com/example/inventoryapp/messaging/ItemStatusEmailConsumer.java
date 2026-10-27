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

    @KafkaListener(topics = "${item.messaging.item-status-topic}", groupId = "${item.security.messaging.email-group}")

    public void receive(ItemStatusEvent event) {
        emailService.sendMessage(event);
    }

}
