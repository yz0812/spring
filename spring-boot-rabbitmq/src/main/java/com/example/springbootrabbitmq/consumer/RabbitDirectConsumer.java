package com.example.springbootrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.springbootrabbitmq.config.RabbitmqConstant.DIRECT_QUEUE1;
import static com.example.springbootrabbitmq.config.RabbitmqConstant.DIRECT_QUEUE2;

@Slf4j
@Component
public class RabbitDirectConsumer {
    @RabbitListener(queues = DIRECT_QUEUE1)
    public void onMessage1(Message message, Channel channel) throws Exception {
        log.info("Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

    @RabbitListener(queues = DIRECT_QUEUE2)
    public void onMessage2(Message message, Channel channel) throws Exception {
        log.info("Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

}
