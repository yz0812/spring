package com.example.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.example.springbootrabbitmq.config.RabbitmqConstant;
@Component
public class WorkCustomer {
    @RabbitListener(queuesToDeclare = @Queue(RabbitmqConstant.WORK_CONNECTION_TOPIC))
    public void receive1(String message){
        System.out.println("work message1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitmqConstant.WORK_CONNECTION_TOPIC))
    public void receive2(String message){
        System.out.println("work message2 = " + message);
    }

}
