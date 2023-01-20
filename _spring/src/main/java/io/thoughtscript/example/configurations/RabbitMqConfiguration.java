package io.thoughtscript.example.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMqConfiguration {

    @Bean
    public Queue blockingQueue() {
        return QueueBuilder.nonDurable("test").build();
    }
}