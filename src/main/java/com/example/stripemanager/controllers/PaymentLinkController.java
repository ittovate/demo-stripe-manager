package com.example.stripemanager.controllers;


import com.example.stripemanager.services.PaymentLinkService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-links")
public class PaymentLinkController {

    @Autowired
    private PaymentLinkService paymentLinkService;


    @PostMapping("/")
    public ResponseEntity<RestResponse<PaymentLink>> createPaymentLink(@RequestBody PaymentLink paymentLink) throws StripeException {
        RestResponse<PaymentLink> response = new RestResponse<>(this.paymentLinkService.creatPaymentLink(paymentLink), "", HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<RestResponse<List<PaymentLink>>> listPaymentLinks(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) String startingAfter
    ) throws StripeException {
        RestResponse<List<PaymentLink>> response = new RestResponse<>(this.paymentLinkService.listPaymentLinks(active, endingBefore, limit, startingAfter), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<PaymentLink>> listPaymentLinks(@PathVariable String id) throws StripeException {
        RestResponse<PaymentLink> response = new RestResponse<>(this.paymentLinkService.getPaymentLinkById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}