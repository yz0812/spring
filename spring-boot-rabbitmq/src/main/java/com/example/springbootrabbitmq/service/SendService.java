package com.example.springbootrabbitmq.service;

import org.springframework.stereotype.Service;

public interface SendService {
    /**
     * @param msg
     * @return void
     * @description 直连模式
     * @author yz
     * @date 2021/9/8 10:49 下午
     * @method senDirectConnection
     */
    void senDirectConnection(String msg);

    /**
     * @param msg
     * @return void
     * @description work模式
     * @author yz
     * @date 2021/9/10 11:36 下午
     * @method senWork
     */
    void senWork(String msg);

    /**
     * @param msg
     * @return void
     * @description 广播
     * @author yz
     * @date 2021/9/10 11:46 下午
     * @method broadcast
     */
    void broadcast(String msg);

    /**
     * @param msg
     * @return void
     * @description 路由模式
     * @author yz
     * @date 2021/9/12 3:04 下午
     * @method route
     */
    void route(String msg, String routingKey);

    void confirmCallback(String msg, String routingKey);

    void senFanoutExchange(String msg);

    void sendDirect(String msg, String routingKey);

    void sendTopic(String msg, String routingKey);

    /**
     * @param msg
     * @return void
     * @description 死信队列
     * @author yz
     * @date 2021/10/18 11:26 下午
     * @method sendMsg
     */
    void sendMsg(String msg);
    /**
     * @description 延时队列
     * @author yz
     * @date 2021/10/27 12:37 上午
     * @method  sendDelayMsg
     * @param msg
    * @param delayTime
     * @return void
     */
    void sendDelayMsg(String msg, Integer delayTime);
}
