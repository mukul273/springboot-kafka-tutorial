package io.chimp.springbootkafkatutorial.controller;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import org.apache.kafka.clients.producer.ProducerConfig;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    String groupId = "my-fourth-application";

    private Properties setProperties() {
        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer .class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer .class.getName());
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("msg") String message) {

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(setProperties());

        // create a producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("first_topic", "This is first message from chimp");

        // send data - asynchronous
        producer.send(producerRecord);
        return ResponseEntity.ok("Message sent to the topic");
    }



}
