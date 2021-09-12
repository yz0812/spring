package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.RabbitmqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ReceiverMessage {

    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue(),
                    key={RabbitmqConstant.ROUTING_KEY_INFO},
                    exchange = @Exchange(type = "direct",name=RabbitmqConstant.CONFIRM_CALLBACK)
            )})
    public void processHandler(String msg, Channel channel, Message message) throws IOException {

        try {
            log.info("小富收到消息：{}", msg);

            //发生异常
            double a = 1/0;
            //TODO 具体业务

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {

                log.error("消息已重复处理失败,拒绝再次接收...");

                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {

                log.error("消息即将再次返回队列处理...");

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
