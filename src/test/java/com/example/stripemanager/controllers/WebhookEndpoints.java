package com.example.stripemanager.controllers;

import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/webhookendpoints")
public class WebhookEndpoints {


    @PostMapping("/")
    public ResponseEntity<String> receiveNotification(@RequestBody String body) {

        logger.info("Webhook received: {}", body);
        return ResponseEntity.ok("Webhook received successfully");
    }
}
