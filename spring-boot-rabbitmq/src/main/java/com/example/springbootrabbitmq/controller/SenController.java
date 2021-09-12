package com.example.springbootrabbitmq.controller;

import com.example.springbootrabbitmq.service.SendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
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
}
