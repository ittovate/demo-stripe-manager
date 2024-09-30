package com.example.stripemanager.controllers;


import com.example.stripemanager.services.PriceService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prices")
public class PriceController {


    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    @PostMapping("/")
    public ResponseEntity<RestResponse<Price>> addPrice(@RequestBody Price price) throws StripeException {
        RestResponse<Price> response = new RestResponse<>(priceService.addPrice(price), "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Price>> getPriceById(@PathVariable String id) throws StripeException {
        RestResponse<Price> response = new RestResponse<>(priceService.getPriceById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/")
    public ResponseEntity<RestResponse<List<Price>>> listPrices(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) String startingAfter,
            @RequestParam(required = false) String url
    ) throws StripeException {

        List<Price> prices = priceService.listPrices(
                active,
                product,
                endingBefore,
                limit,
                startingAfter
        );

        RestResponse<List<Price>> response = new RestResponse<>(prices, "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
