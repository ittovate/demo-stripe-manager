package com.example.stripemanager.controllers;


import com.example.stripemanager.services.InvoiceItemsService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-items")
public class InvoiceItemsController {
    private final InvoiceItemsService invoiceItemsService;

    public InvoiceItemsController(InvoiceItemsService invoiceItemsService) {
        this.invoiceItemsService = invoiceItemsService;
    }


    @PostMapping("/")
    public ResponseEntity<RestResponse<InvoiceItem>> createInvoice(
            @RequestBody InvoiceItem invoiceItem
    ) throws StripeException {
        RestResponse<InvoiceItem> response = new RestResponse<>(invoiceItemsService.createInvoiceItem(invoiceItem), "", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<InvoiceItem>> getInvoiceById(@PathVariable String id) throws StripeException {
        RestResponse<InvoiceItem> response = new RestResponse<>(invoiceItemsService.getInvoiceItemById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<RestResponse<List<InvoiceItem>>> listInvoices(
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String invoice,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) Boolean pending

    ) throws StripeException {
        RestResponse<List<InvoiceItem>> response = new RestResponse<>(invoiceItemsService.listInvoiceItems(customer, invoice, limit, pending), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
