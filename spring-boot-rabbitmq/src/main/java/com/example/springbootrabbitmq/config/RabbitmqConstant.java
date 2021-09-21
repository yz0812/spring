package com.example.springbootrabbitmq.config;

public interface RabbitmqConstant {
    /**
     * 直连模式
     */
    String DIRECT_CONNECTION_TOPIC = "topic.direct";
    /**
     * 多个消费
     */
    String WORK_CONNECTION_TOPIC = "topic.work";
    /**
     * 广播
     */
    String BROADCAST_TOPIC = "topic.broadcast";

    String FANOUT_TOPIC = "fanout";

    /**
     * 路由
     */
    String ROUTE_TOPIC = "topic.route";


    String QUEUE_FANOUT1 = "queue.fanout1";

    String QUEUE_FANOUT2 = "queue.fanout2";

    String FANOUT_EXCHANG = "fanoutExchange";

    String DIRECT_QUEUE1 = "queue.direct1";

    String DIRECT_QUEUE2 = "queue.direct2";

    String DIRECT_EXCHANGE = "directExchange";

    String ROUTING_KEY_ERROR = "error";

    String ROUTING_KEY_INFO = "info";

    String TOPIC_FANOUT1 = "topic.fanout1";

    String TOPIC_FANOUT2 = "topic.fanout2";

    String TOPIC_EXCHANG = "topicExchange";

    String ROUTING_KEY_ERROR_ONE = "log.error.*";

    String ROUTING_KEY_INFO_ALL = "log.info.#";

    String CONFIRM_CALLBACK = "topic.confirm.callback";

    String CONFIRM_CALLBACK_QUEUE = "topic.confirm.callback.queue";
}
