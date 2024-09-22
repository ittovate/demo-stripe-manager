package com.example.stripemanager.controllers;


import com.example.stripemanager.services.BalanceService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Balance;
import com.stripe.model.BalanceTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @Operation(
            summary = "Retrieve the Balance Object.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Balance Object retrieved successfully.")
            }
    )
    @GetMapping("/")
    public ResponseEntity<RestResponse<Balance>> getAccountBalance() throws StripeException {
        RestResponse<Balance> response = new RestResponse<>(balanceService.getBalance(), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @Operation(
            summary = "Retrieve a list of Balance transactions.",
            description = "Returns a list of transactions that have contributed to " +
                    "the Stripe account balance (e.g., charges, transfers, and so forth). " +
                    "The transactions are returned in sorted order, with the most recent transactions appearing first.",

            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Balance transactions retrieved successfully.")
            }
    )
    @GetMapping("/balance-transactions")
    public ResponseEntity<RestResponse<List<BalanceTransaction>>> getBalanceTransactions(
            @RequestParam(required = false) String payout,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long limit
    ) throws StripeException {
        RestResponse<List<BalanceTransaction>> response = new RestResponse<>(balanceService.listBalanceTransactions(payout, type, limit), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieves the balance transaction with the given ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Balance transaction item retrieved successfully.")
            }
    )
    @GetMapping("/balance-transactions/{id}")
    public ResponseEntity<RestResponse<BalanceTransaction>> getBalanceTransactionById(@PathVariable String id) throws StripeException {
        RestResponse<BalanceTransaction> response = new RestResponse<>(balanceService.getBalanceTransactionById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
