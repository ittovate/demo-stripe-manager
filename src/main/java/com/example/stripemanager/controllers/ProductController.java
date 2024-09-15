package com.example.stripemanager.controllers;


import com.example.stripemanager.services.ProductService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(@Value("${stripe.apiKey}")
                             String apiKey) {
        Stripe.apiKey = apiKey;
    }

    @PostMapping("/")
    public ResponseEntity<RestResponse<Product>> addProduct(@RequestBody Product product) throws StripeException {
        RestResponse<Product> response = new RestResponse<>(productService.addProduct(product), "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Product>> getProductById(@PathVariable String id) throws StripeException {
        RestResponse<Product> response = new RestResponse<>(productService.getProductById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/")
    public ResponseEntity<RestResponse<List<Product>>> listProducts(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Map<String, Long> created,
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) String[] ids,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) Boolean shippable,
            @RequestParam(required = false) String startingAfter,
            @RequestParam(required = false) String url
    ) throws StripeException {

        List<Product> products = productService.listProducts(
                active,
                created,
                endingBefore,
                limit,
                shippable,
                startingAfter,
                url
        );

        RestResponse<List<Product>> response = new RestResponse<>(products, "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
