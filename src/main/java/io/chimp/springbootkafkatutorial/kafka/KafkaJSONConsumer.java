package io.chimp.springbootkafkatutorial.kafka;

import io.chimp.springbootkafkatutorial.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaJSONConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.json_name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user){
        log.info(String.format("JSON message received ->"+user.toString()));
    }
}
