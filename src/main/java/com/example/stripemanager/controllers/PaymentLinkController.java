package com.example.stripemanager.controllers;


import com.example.stripemanager.dto.PaymentLinkDTO;
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


    private final PaymentLinkService paymentLinkService;

    public PaymentLinkController(PaymentLinkService paymentLinkService) {
        this.paymentLinkService = paymentLinkService;
    }


    @PostMapping("/")
    public ResponseEntity<RestResponse<PaymentLinkDTO>> createPaymentLink(@RequestBody PaymentLink paymentLink) throws StripeException {
        RestResponse<PaymentLinkDTO> response = new RestResponse<>(this.paymentLinkService.creatPaymentLink(paymentLink), "", HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<RestResponse<List<PaymentLinkDTO>>> listPaymentLink(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) String startingAfter
    ) throws StripeException {
        RestResponse<List<PaymentLinkDTO>> response = new RestResponse<>(this.paymentLinkService.listPaymentLinks(active, endingBefore, limit, startingAfter), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<PaymentLinkDTO>> listPaymentLinks(@PathVariable String id) throws StripeException {
        RestResponse<PaymentLinkDTO> response = new RestResponse<>(this.paymentLinkService.getPaymentLinkById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
