package io.chimp.springbootkafkatutorial.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = "first_topic", groupId = "${spring.kafka.consumer.group-id}" )
    public void consume(String message) {
      log.info("message received at consumer::"+message);
    }
}
