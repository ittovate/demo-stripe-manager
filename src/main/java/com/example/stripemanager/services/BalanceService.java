package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.Balance;
import com.stripe.model.BalanceTransaction;
import com.stripe.param.BalanceTransactionListParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {


    /**
     * This is an object representing your Stripe balance. You can retrieve it to see the balance currently on your Stripe account.
     *
     * @return
     * @throws StripeException
     */
    public Balance getBalance() throws StripeException {
        return Balance.retrieve();
    }


    /**
     * Returns a list of transactions that have contributed to the Stripe account
     * balance (e.g., charges, transfers, and so forth). The transactions are returned in sorted order,
     * with the most recent transactions appearing first.
     *
     * @param payout
     * @param type
     * @param limit
     * @return a list of balance transactions that match the specified filters (payout, type, limit)
     * @throws StripeException
     */
    public List<BalanceTransaction> listBalanceTransactions(
            String payout,
            String type,
            Long limit
    ) throws StripeException {
        BalanceTransactionListParams params = BalanceTransactionListParams.builder()
                .setLimit(limit)
                .setPayout(payout)
                .setType(type)
                .build();

        return BalanceTransaction.list(params).getData();

    }


    /**
     * Retrieves the balance transaction with the given ID.
     * @param id
     * @return a balance transaction if a valid balance transaction ID was provided. Throws an <a href="https://docs.stripe.com/api/errors?lang=java">error</a> otherwise.
     * @throws StripeException
     */
    public BalanceTransaction getBalanceTransactionById(String id) throws StripeException {
        return BalanceTransaction.retrieve(id);
    }
}
