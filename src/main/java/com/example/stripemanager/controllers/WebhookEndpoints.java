package com.example.stripemanager.controllers;

import com.example.stripemanager.constants.StringConstant;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhookendpoints")
public class WebhookEndpoints {

    private static final Logger logger =  LoggerFactory.getLogger(WebhookEndpoints.class);

    @PostMapping("/")
    public ResponseEntity<String> receiveNotification(@RequestBody String body) {

        logger.info(StringConstant.WEBHOOK_RECEIVED, body);

        return  ResponseEntity.ok(StringConstant.WEBHOOK_RESPONSE_MESSAGE);
    }
}