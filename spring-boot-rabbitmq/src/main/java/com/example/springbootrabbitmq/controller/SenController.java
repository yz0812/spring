package com.example.springbootrabbitmq.controller;

import com.example.springbootrabbitmq.service.SendService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
@Slf4j
public class SenController {
    private final SendService sendService;

    @GetMapping("sen")
    public void senDirect(String msg) {
        sendService.senDirectConnection(msg);
    }

    @GetMapping("work")
    public void senWork(String msg) {
        sendService.senWork(msg);
    }

    @GetMapping("broadcast")
    public void senBroadcast(String msg) {
        sendService.broadcast(msg);
    }


    @GetMapping("route")
    public void senRoute(String msg,String routingKey) {
        sendService.route(msg,routingKey);
    }

    @GetMapping("confirmCallback")
    public void confirmCallback(String msg,String routingKey) {
        sendService.confirmCallback(msg,routingKey);
    }

    @GetMapping("fanoutExchange")
    public void fanoutExchange(String msg) {
        sendService.senFanoutExchange(msg);
    }

    @GetMapping("sendDirect")
    public void sendDirect(String msg,String routingKey) {
        sendService.sendDirect(msg,routingKey);
    }

    @GetMapping("sendTopic")
    public void sendTopic(String msg,String routingKey) {
        sendService.sendTopic(msg,routingKey);
    }

    @GetMapping("sendmsg") public void sendMsg(String msg){ sendService.sendMsg(msg); }

    @GetMapping("delayMsg")
    public void delayMsg2(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        sendService.sendDelayMsg(msg, delayTime);
    }
}
