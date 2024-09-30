package com.example.stripemanager.controllers;


import com.example.stripemanager.dto.ProductDTO;
import com.example.stripemanager.dto.ProductResponseDTO;
import com.example.stripemanager.services.ProductService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping("/")
    public ResponseEntity<RestResponse<ProductResponseDTO>> addProduct(@RequestBody ProductDTO product) throws StripeException {

        RestResponse<ProductResponseDTO> response = new RestResponse<>(productService.addProduct(product), "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ProductResponseDTO>> getProductById(@PathVariable String id) throws StripeException {
        RestResponse<ProductResponseDTO> response = new RestResponse<>(productService.getProductById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/")
    public ResponseEntity<RestResponse<List<ProductResponseDTO>>> listProducts(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) Map<String, Long> created,
            @RequestParam(required = false) String endingBefore,
            @RequestParam(required = false) String[] ids,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) Boolean shippable,
            @RequestParam(required = false) String startingAfter,
            @RequestParam(required = false) String url
    ) throws StripeException {

        List<ProductResponseDTO> products = productService.listProducts(
                active,
                created,
                endingBefore,
                limit,
                shippable,
                startingAfter,
                url
        );

        RestResponse<List<ProductResponseDTO>> response = new RestResponse<>(products, "", HttpStatus.ACCEPTED);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}
