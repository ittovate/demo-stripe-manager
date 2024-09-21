package com.example.stripemanager.controllers;


import com.example.stripemanager.services.BalanceService;
import com.example.stripemanager.utils.RestResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Balance;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.param.BalanceTransactionListParams;
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

    @GetMapping("/")
    public ResponseEntity<RestResponse<Balance>> getAccountBalance() throws StripeException {
        RestResponse<Balance> response = new RestResponse<>(balanceService.getBalance(), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/balance-transactions")
    public ResponseEntity<RestResponse<List<BalanceTransaction>>> getBalanceTransactions(
            @RequestParam(required = false) String payout,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long limit
    ) throws StripeException {
        RestResponse<List<BalanceTransaction>> response = new RestResponse<>(balanceService.listBalanceTransactions(payout, type, limit), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/balance-transactions/{id}")
    public ResponseEntity<RestResponse<BalanceTransaction>>  getBalanceTransactionById(@PathVariable String id) throws StripeException {
        RestResponse<BalanceTransaction> response = new RestResponse<>(balanceService.getBalanceTransactionById(id), "", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
