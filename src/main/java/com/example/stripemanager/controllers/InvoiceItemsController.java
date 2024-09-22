package com.example.stripemanager.controllers;


import com.example.stripemanager.services.InvoiceItemsService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-items")
public class InvoiceItemsController {

    @Autowired
    private InvoiceItemsService invoiceItemsService;


    @Operation(
            ignoreJsonView = true,
            hidden = true
    )
    @PostMapping("/")
    public ResponseEntity<RestResponse<InvoiceItem>> createInvoice(
            @RequestBody InvoiceItem invoiceItem
    ) throws StripeException {
        RestResponse<InvoiceItem> response = new RestResponse<>(invoiceItemsService.createInvoiceItem(invoiceItem), "", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Operation(
            ignoreJsonView = true,
            hidden = true
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<InvoiceItem>> getInvoiceById(@PathVariable String id) throws StripeException {
        RestResponse<InvoiceItem> response = new RestResponse<>(invoiceItemsService.getInvoiceItemById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(
            summary = "List all invoices.",
            description = "ist all invoices, or list the invoices for a specific customer. " +
                    "The invoices are returned sorted by creation date, with the most recently created invoices appearing first.",
            parameters = {
                    @Parameter(
                            name = "customer",
                            description = "Only return invoices for the customer specified by this customer ID.",
                            schema = @Schema(oneOf = String.class)
                    ),
                    @Parameter(
                            name = "status",
                            description = "The status of the invoice, one of `draft`, `open`, `paid`, `uncollectible`, or `void`",
                            schema = @Schema(oneOf = Enum.class)
                    ),
                    @Parameter(
                            name = "subscription",
                            description = "Only return invoices for the subscription specified by this subscription ID.",
                            schema = @Schema(oneOf = String.class)
                    ),
                    @Parameter(
                            name = "limit",
                            description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.",
                            schema = @Schema(oneOf = Long.class)
                    )
            },

            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Returns an array invoice attachments retrieved successfully.",
                            content = @Content(schema = @Schema(oneOf = String.class)))
            }
    )
    @GetMapping("/")
    public ResponseEntity<RestResponse<List<InvoiceItem>>> listInvoiceItems(
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String invoice,
            @RequestParam(required = false) Long limit,
            @RequestParam(required = false) Boolean pending

    ) throws StripeException {
        RestResponse<List<InvoiceItem>> response = new RestResponse<>(invoiceItemsService.listInvoiceItems(customer, invoice, limit, pending), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
