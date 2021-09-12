package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.RabbitmqConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitmqConstant.DIRECT_CONNECTION_TOPIC))
public class HelloCustomer {

    @RabbitHandler
    public void receive1(String message) {
        System.out.println("message = " + message);
    }
}
