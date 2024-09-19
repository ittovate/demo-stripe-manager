package com.example.stripemanager.controllers;

import com.example.stripemanager.services.InvoiceService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/")
    public ResponseEntity<RestResponse<Invoice>> createInvoice(
            @RequestBody Invoice invoice
    ) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.createInvoice(invoice), "", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Invoice>> getInvoiceById(@PathVariable String id) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.getInvoiceById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<RestResponse<List<Invoice>>> listInvoices(
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String subscription,
            @RequestParam(required = false) Long limit

    ) throws StripeException {
        RestResponse<List<Invoice>> response = new RestResponse<>(invoiceService.listInvoices(customer, status, subscription, limit), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{id}/send")
    public ResponseEntity<RestResponse<Invoice>> sendInvoiceForManualPAyment(@PathVariable String id) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.sendInvoiceForManualPayment(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/finalize")
    public ResponseEntity<RestResponse<Invoice>> finalizeInvoice(
            @PathVariable String id,
            @RequestParam(required = false) Boolean autoAdvance
    ) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.finalizeInvoice(id, autoAdvance), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{id}/pay")
    public ResponseEntity<RestResponse<Invoice>> payInvoice(
            @PathVariable String id,
            @RequestParam(required = false) Boolean forgive,
            @RequestParam(required = false) String paymentMethod
    ) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.payInvoice(id, forgive, paymentMethod), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
