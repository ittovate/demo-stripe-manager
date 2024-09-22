package com.example.stripemanager.controllers;

import com.example.stripemanager.services.InvoiceService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

//    @Operation(
//            summary = "Creates a draft invoice for a given customer.",
//            description = "This endpoint creates a draft invoice for a given customer. " +
//                    "The invoice remains a draft until you finalize the invoice, " +
//                    "which allows you to pay or send the invoice to your customers.",
//
//            responses = {
//                    @ApiResponse(responseCode = "201",
//                            description = "Returns the successfully created invoice object.")
//            }
//    )
    @PostMapping("/")
    public ResponseEntity<RestResponse<Invoice>> createInvoice(
            @RequestBody Invoice invoice
    ) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.createInvoice(invoice), "", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


//    @Operation(
//            summary = "Retrieve a list of Balance transactions.",
//            description = "Returns a list of transactions that have contributed to " +
//                    "the Stripe account balance (e.g., charges, transfers, and so forth). " +
//                    "The transactions are returned in sorted order, with the most recent transactions appearing first.",
//
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Balance transactions retrieved successfully.")
//            }
//    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Invoice>> getInvoiceById(@PathVariable String id) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.getInvoiceById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//
//    @Operation(
//            summary = "List all invoices.",
//            description = "ist all invoices, or list the invoices for a specific customer. " +
//                    "The invoices are returned sorted by creation date, with the most recently created invoices appearing first.",
//            parameters = {
//                    @Parameter(
//                            name = "customer",
//                            description = "Only return invoices for the customer specified by this customer ID.",
//                            schema = @Schema(oneOf = String.class)
//                    ),
//                    @Parameter(
//                            name = "status",
//                            description = "The status of the invoice, one of `draft`, `open`, `paid`, `uncollectible`, or `void`",
//                            schema = @Schema(oneOf = Enum.class)
//                    ),
//                    @Parameter(
//                            name = "subscription",
//                            description = "Only return invoices for the subscription specified by this subscription ID.",
//                            schema = @Schema(oneOf = String.class)
//                    ),
//                    @Parameter(
//                            name = "limit",
//                            description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.",
//                            schema = @Schema(oneOf = Long.class)
//                    )
//            },
//
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Returns an array invoice attachments retrieved successfully.")
//            }
//    )
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

//
//    @Operation(
//            summary = "Retrieve a list of Balance transactions.",
//            description = "Returns a list of transactions that have contributed to " +
//                    "the Stripe account balance (e.g., charges, transfers, and so forth). " +
//                    "The transactions are returned in sorted order, with the most recent transactions appearing first.",
//
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Balance transactions retrieved successfully.")
//            }
//    )
    @PostMapping("/{id}/send")
    public ResponseEntity<RestResponse<Invoice>> sendInvoiceForManualPAyment(@PathVariable String id) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.sendInvoiceForManualPayment(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @Operation(
//            summary = "Retrieve a list of Balance transactions.",
//            description = "Returns a list of transactions that have contributed to " +
//                    "the Stripe account balance (e.g., charges, transfers, and so forth). " +
//                    "The transactions are returned in sorted order, with the most recent transactions appearing first.",
//
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Balance transactions retrieved successfully.")
//            }
//    )
    @PostMapping("/{id}/finalize")
    public ResponseEntity<RestResponse<Invoice>> finalizeInvoice(
            @PathVariable String id,
            @RequestParam(required = false) Boolean autoAdvance
    ) throws StripeException {
        RestResponse<Invoice> response = new RestResponse<>(invoiceService.finalizeInvoice(id, autoAdvance), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @Operation(
//            summary = "Retrieve a list of Balance transactions.",
//            description = "Returns a list of transactions that have contributed to " +
//                    "the Stripe account balance (e.g., charges, transfers, and so forth). " +
//                    "The transactions are returned in sorted order, with the most recent transactions appearing first.",
//
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Balance transactions retrieved successfully.")
//            }
//    )
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
