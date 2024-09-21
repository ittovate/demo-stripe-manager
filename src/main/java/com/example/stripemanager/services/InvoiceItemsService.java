package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import com.stripe.param.InvoiceItemCreateParams;
import com.stripe.param.InvoiceItemListParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemsService {


    /**
     * Creates an item to be added to a draft invoice (up to 250 items per invoice).
     * If no invoice is specified, the item will be on the next invoice created for the customer specified.
     *
     * @param invoiceItem
     * @return
     * @throws StripeException
     */
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) throws StripeException {

        InvoiceItemCreateParams params = InvoiceItemCreateParams.builder()
                .setCustomer(invoiceItem.getCustomer())
                .setPrice(invoiceItem.getPrice().getId())
                .build();

        return InvoiceItem.create(params);
    }


    /**
     * Retrieves the invoice item with the given ID.
     * @param id
     * @return
     * @throws StripeException
     */
    public InvoiceItem getInvoiceItemById(String id) throws StripeException {
        return InvoiceItem.retrieve(id);
    }


    /**
     * Returns a list of your invoice items. Invoice items are returned sorted by creation date,
     * with the most recently created invoice items appearing first.
     * @param customer
     * @param invoice
     * @param limit
     * @param pending
     * @return
     */
    public List<InvoiceItem> listInvoiceItems(
            String customer,
            String invoice,
            Long limit,
            Boolean pending
    ) throws StripeException {

        InvoiceItemListParams params = InvoiceItemListParams.builder()
                .setCustomer(customer)
                .setInvoice(invoice)
                .setLimit(limit)
                .setPending(pending)
                .build();

        return InvoiceItem.list(params).getData();

    }
}
