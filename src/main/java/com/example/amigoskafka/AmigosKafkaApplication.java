package com.example.amigoskafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class AmigosKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmigosKafkaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                kafkaTemplate.send("amigos-code", "hello world! " + i);
                System.out.println("SUCCESSFULLY sent it! " + i);
            }
        };
    }
}
