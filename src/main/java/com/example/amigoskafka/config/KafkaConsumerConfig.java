package com.example.amigoskafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.apache.kafka.bootstrap-server}")
    private String bootstrapServer;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> property = new HashMap<>();
        property.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        property.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, DefaultSerializer.class);
        property.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DefaultSerializer.class);

        return property;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(
            ConsumerFactory<String, String> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;

    }
}
