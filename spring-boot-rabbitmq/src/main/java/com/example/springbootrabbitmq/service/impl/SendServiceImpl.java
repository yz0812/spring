package com.example.springbootrabbitmq.service.impl;

import com.example.springbootrabbitmq.config.ConfirmCallbackService;
import com.example.springbootrabbitmq.config.RabbitmqConstant;
import com.example.springbootrabbitmq.config.ReturnCallbackService;
import com.example.springbootrabbitmq.service.SendService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.springbootrabbitmq.config.RabbitmqConstant.*;

@Service
@AllArgsConstructor
@Slf4j
public class SendServiceImpl implements SendService {

    private final RabbitTemplate rabbitTemplate;

    private final ConfirmCallbackService confirmCallbackService;

    private final ReturnCallbackService returnCallbackService;

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

    @Override
    public void confirmCallback(String msg,String routingKey) {
        /**
         * 确保消息发送失败后可以重新返回到队列中
         * 注意：yml需要配置 publisher-returns: true
         */
        rabbitTemplate.setMandatory(true);

        /**
         * 消费者确认收到消息后，手动ack回执回调处理
         */
        rabbitTemplate.setConfirmCallback(confirmCallbackService);

        /**
         * 消息投递到队列失败回调处理
         */
        rabbitTemplate.setReturnCallback(returnCallbackService);

        /**
         * 发送消息
         */
        rabbitTemplate.convertAndSend(RabbitmqConstant.CONFIRM_CALLBACK,routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));

    }

    @Override
    public void senFanoutExchange(String msg) {
        rabbitTemplate.convertAndSend(FANOUT_EXCHANG,null,msg);
        System.out.println("消息发送完毕。");
    }

    @Override
    public void sendDirect(String msg,String routingKey) {
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,routingKey,msg);
    }

    @Override
    public void sendTopic(String msg,String routingKey) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANG,routingKey,msg);
    }
}
