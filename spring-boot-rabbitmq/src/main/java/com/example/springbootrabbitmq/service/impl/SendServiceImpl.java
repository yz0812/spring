package com.example.springbootrabbitmq.service.impl;

import com.example.springbootrabbitmq.config.RabbitmqConstant;
import com.example.springbootrabbitmq.service.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class SendServiceImpl implements SendService {

    private final RabbitTemplate rabbitTemplate;


    @Override
    public void senDirectConnection(String msg) {
        rabbitTemplate.convertAndSend(RabbitmqConstant.DIRECT_CONNECTION_TOPIC,msg);
    }

    @Override
    public void senWork(String msg) {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitmqConstant.WORK_CONNECTION_TOPIC,i+":"+msg);
        }
    }

    @Override
    public void broadcast(String msg) {
        rabbitTemplate.convertAndSend(RabbitmqConstant.BROADCAST_TOPIC,"",msg);
    }

    @Override
    public void route(String msg,String routingKey) {
        rabbitTemplate.convertAndSend(RabbitmqConstant.ROUTE_TOPIC,routingKey,msg);
    }
}
