package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.example.springbootrabbitmq.config.RabbitmqConstant.*;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue fanout1() {
        return new Queue(QUEUE_FANOUT1);
    }

    @Bean
    public Queue fanout2() {
        return new Queue(QUEUE_FANOUT2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        // 三个构造参数：name durable autoDelete
        return new FanoutExchange(FANOUT_EXCHANG, false, false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(fanout1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(fanout2()).to(fanoutExchange());
    }

    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2);
    }

    @Bean
    public DirectExchange directExchange() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }

    /**
     * 绑定到routingKey
     */
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(ROUTING_KEY_INFO);
    }

    /**
     * 绑定到routingKey
     */
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(ROUTING_KEY_ERROR);
    }


    // 主题交换机示例
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_FANOUT1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_FANOUT2);
    }

    @Bean
    public TopicExchange topicExchange() {
        // 三个构造参数：name durable autoDelete
        return new TopicExchange(TOPIC_EXCHANG, false, false);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY_INFO_ALL);
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY_ERROR_ONE);
    }


    /**
     * value      队列名称
     * durable    是否持久化
     * exclusive  否为独占队列
     * autoDelete 是否自动删除
     */
    @Bean
    public Queue topicConfirmCallbackQueue() {
        // 其三个参数：durable exclusive autoDelete
        return new Queue(CONFIRM_CALLBACK_QUEUE, true);
    }

    /**
     * value      交换机名称
     * type       交换机类型，默认 direct
     * durable    是否持久化，默认 true
     * autoDelete 是否自动删除，默认 false
     * internal   是否为内部交换机，默认为 false
     */

    @Bean
    public DirectExchange topicConfirmCallbackExchange() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange(CONFIRM_CALLBACK, true, false);
    }

    @Bean
    public Binding topicConfirmCallbackBinding() {
        return BindingBuilder.bind(topicConfirmCallbackQueue()).to(topicConfirmCallbackExchange()).with(ROUTING_KEY_INFO);
    }



    // 声明业务Exchange
    @Bean("businessExchange")
    public FanoutExchange businessExchange() {
        return new FanoutExchange(BUSINESS_EXCHANGE_NAME);
    }

    // 声明死信Exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    // 声明业务队列A
    @Bean("businessQueueA")
    public Queue businessQueueA() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange 这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key 这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEA_ROUTING_KEY);

        return QueueBuilder.durable(BUSINESS_QUEUEA_NAME).withArguments(args).build();
    }

    // 声明业务队列B
    @Bean("businessQueueB")
    public Queue businessQueueB() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange 这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key 这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUEB_ROUTING_KEY);
        return QueueBuilder.durable(BUSINESS_QUEUEB_NAME).withArguments(args).build();
    }

    // 声明死信队列A
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA() {
        return new Queue(DEAD_LETTER_QUEUEA_NAME);
    }

    // 声明死信队列B
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB() {
        return new Queue(DEAD_LETTER_QUEUEB_NAME);
    }

    // 声明业务队列A绑定关系
    @Bean
    public Binding businessBindingA(@Qualifier("businessQueueA") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    // 声明业务队列B绑定关系
    @Bean
    public Binding businessBindingB(@Qualifier("businessQueueB") Queue queue, @Qualifier("businessExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    // 声明死信队列A绑定关系
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueueA") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEA_ROUTING_KEY);
    }

    // 声明死信队列B绑定关系
    @Bean
    public Binding deadLetterBindingB(@Qualifier("deadLetterQueueB") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUEB_ROUTING_KEY);
    }



    @Bean
    public Queue immediateQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify(@Qualifier("immediateQueue") Queue queue,
                                 @Qualifier("customExchange") CustomExchange customExchange) {
        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
