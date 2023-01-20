package io.thoughtscript.example.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqListener {
    @RabbitListener(queues = "test")
    public void listen(String message) {
        log.info(message);
    }
}