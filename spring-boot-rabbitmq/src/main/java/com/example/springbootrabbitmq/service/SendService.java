package com.example.springbootrabbitmq.service;

import org.springframework.stereotype.Service;

public interface SendService {
    /**
     * @description 直连模式
     * @author yz
     * @date 2021/9/8 10:49 下午
     * @method  senDirectConnection
     * @param msg
     * @return void
     */
    void senDirectConnection(String msg);
    /**
     * @description work模式
     * @author yz
     * @date 2021/9/10 11:36 下午
     * @method  senWork
     * @param msg
     * @return void
     */
    void senWork(String msg);

    /**
     * @description 广播
     * @author yz
     * @date 2021/9/10 11:46 下午
     * @method  broadcast
     * @param msg
     * @return void
     */
    void broadcast(String msg);

    /**
     * @description 路由模式
     * @author yz
     * @date 2021/9/12 3:04 下午
     * @method  route
     * @param msg
     * @return void
     */
    void route(String msg,String routingKey);

    void confirmCallback(String msg,String routingKey);

    void senFanoutExchange(String msg);

    void sendDirect(String msg,String routingKey);

    void sendTopic(String msg,String routingKey);
}
