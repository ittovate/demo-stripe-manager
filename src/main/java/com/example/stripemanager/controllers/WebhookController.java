package com.example.stripemanager.controllers;

import com.example.stripemanager.services.WebhookService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.WebhookEndpoint;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {


    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/")
    public ResponseEntity<RestResponse<WebhookEndpoint>> addWebhookEndpoint(@RequestBody WebhookEndpoint webhook) throws StripeException {
        RestResponse<WebhookEndpoint> response = new RestResponse<>(webhookService.createWebhook(webhook), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<RestResponse<List<WebhookEndpoint>>> listWebhookEndpoints(
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) String startingAfter
    ) throws StripeException {
        RestResponse<List<WebhookEndpoint>> response = new RestResponse<>(webhookService.listWebhooks(endingBefore, limit, startingAfter), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<WebhookEndpoint>> getWebhookEndpointById(@PathVariable String id) throws StripeException {
        RestResponse<WebhookEndpoint> response = new RestResponse<>(webhookService.getWebhookById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
