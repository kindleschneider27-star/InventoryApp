package com.example.inventoryapp.messaging;

import com.example.inventoryapp.config.EmailProperties;
import com.example.inventoryapp.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ItemStatusEmailService {
    private static final Logger logger = LoggerFactory.getLogger(ItemStatusEmailService.class);
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final EmailProperties emailProperties;

    public ItemStatusEmailService(JavaMailSender jms, UserRepository ur, EmailProperties ep) {
        mailSender = jms;
        userRepository = ur;
        emailProperties = ep;
    }

    public void sendMessage(ItemStatusEvent event) {
        String to="managers@myinventoryapp.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailProperties.getFromAddress());
        message.setTo(emailProperties.getFromAddress());
        message.setBcc(to);
        message.setSubject(event.getStatus().toString() + " - " + event.getItemName() + " - " + event.getItemInventory());
        message.setText(event.toStatusEmailTText());

        mailSender.send(message);

        logger.info("Sent email for {}", event.getEventId());
    }
}
