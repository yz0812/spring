package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.RabbitmqConstant;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCustomer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name= RabbitmqConstant.BROADCAST_TOPIC,type = RabbitmqConstant.FANOUT_TOPIC)
    ))
    public void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, //创建临时队列
            exchange = @Exchange(name=RabbitmqConstant.BROADCAST_TOPIC,type = RabbitmqConstant.FANOUT_TOPIC)  //绑定交换机类型
    ))
    public void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
