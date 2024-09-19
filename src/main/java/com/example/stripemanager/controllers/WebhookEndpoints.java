package com.example.stripemanager.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhookendpoints")
public class WebhookEndpoints {

    @PostMapping("/")
    public String receiveNotification(@RequestBody String body) {
        System.out.println("received body is   " + body);
        return "lol";
    }
}
