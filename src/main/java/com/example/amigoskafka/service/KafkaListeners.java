package com.example.amigoskafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListeners {


    @KafkaListener(topics = "amigos-code",
            groupId = "groupId")
    public void consumer(String message) {
        System.out.println("Listener received -> " + message);
    }
}
