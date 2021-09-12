package com.example.springbootrabbitmq.config;

public interface RabbitmqConstant {
    /**
     * 直连模式
     */
    String DIRECT_CONNECTION_TOPIC ="topic.direct";
    /**
     * 多个消费
     */
    String WORK_CONNECTION_TOPIC ="topic.work";
    /**
     * 广播
     */
    String BROADCAST_TOPIC = "topic.broadcast";

    String FANOUT_TOPIC = "fanout";

    /**
     * 路由
     */
    String ROUTE_TOPIC = "topic.route";

    /**
     *
     */
    String ROUTING_KEY_ERROR ="error";
    /**
     *
     */
    String ROUTING_KEY_INFO ="info";
}
