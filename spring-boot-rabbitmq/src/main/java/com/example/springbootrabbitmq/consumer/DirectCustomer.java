package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.RabbitmqConstant;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectCustomer {

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(),
                    key={RabbitmqConstant.ROUTING_KEY_ERROR,RabbitmqConstant.ROUTING_KEY_INFO},
                    exchange = @Exchange(type = "direct",name=RabbitmqConstant.ROUTE_TOPIC)
            )})
    public void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(),
                    key={RabbitmqConstant.ROUTING_KEY_INFO},
                    exchange = @Exchange(type = "direct",name=RabbitmqConstant.ROUTE_TOPIC)
            )})
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
