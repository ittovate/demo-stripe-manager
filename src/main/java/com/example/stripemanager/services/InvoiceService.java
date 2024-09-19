package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.param.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {


    /**
     * This endpoint creates a draft invoice for a given customer.
     * The invoice remains a draft until you <a href="https://docs.stripe.com/api/invoices/create?lang=java#finalize_invoice">finalize</a> the invoice, which allows you to <a href="https://docs.stripe.com/api/invoices/create?lang=java#pay_invoice">pay</a> or <a href="https://docs.stripe.com/api/invoices/create?lang=java#send_invoice">send</a> the invoice to your customers.
     *
     * @param invoice <a href="https://docs.stripe.com/api/invoices/object">invoice</a>
     * @return
     */
    public Invoice createInvoice(Invoice invoice) throws StripeException {
        InvoiceCreateParams.CollectionMethod collectionMethod;

        if (invoice.getCollectionMethod().equals("CHARGE_AUTOMATICALLY".toLowerCase())) {
            collectionMethod = InvoiceCreateParams.CollectionMethod.CHARGE_AUTOMATICALLY;
        } else {
            collectionMethod = InvoiceCreateParams.CollectionMethod.SEND_INVOICE;
        }

        InvoiceCreateParams params = InvoiceCreateParams.builder()
                .setAutoAdvance(invoice.getAutoAdvance())
                .setCollectionMethod(collectionMethod)
                .setCustomer(invoice.getCustomer())
                .setDescription(invoice.getDescription())
                .setMetadata(invoice.getMetadata())
                .setSubscription(invoice.getSubscription())
                .setDefaultPaymentMethod(invoice.getDefaultPaymentMethod())
                .setDueDate(invoice.getDueDate()) //  Valid only for invoices where `collection_method=send_invoice`.
                .setEffectiveAt(invoice.getEffectiveAt())
                .build();

        return Invoice.create(params);

    }


    /**
     * Retrieves the invoice with the given ID.
     *
     * @param id
     * @return
     * @throws StripeException
     */
    public Invoice getInvoiceById(String id) throws StripeException {
        return Invoice.retrieve(id);
    }


    /**
     * You can list all invoices, or list the invoices for a specific customer.
     * The invoices are returned sorted by creation date, with the most recently created invoices appearing first.
     *
     * @return
     * @throws StripeException
     */
    public List<Invoice> listInvoices(
            String customer,
            String status,
            String subscription,
            Long limit
    ) throws StripeException {
        InvoiceListParams.Status statusEnum;

        switch (status) {
            case "open":
                statusEnum = InvoiceListParams.Status.OPEN;
                break;
            case "draft":
                statusEnum = InvoiceListParams.Status.DRAFT;
                break;
            case "paid":
                statusEnum = InvoiceListParams.Status.PAID;
                break;
            case "uncollectable":
                statusEnum = InvoiceListParams.Status.UNCOLLECTIBLE;
                break;
            case "void":
                statusEnum = InvoiceListParams.Status.VOID;
                break;
            default:
                statusEnum = null;
        }

        InvoiceListParams params = InvoiceListParams.builder()
                .setCustomer(customer)
                .setStatus(statusEnum)
                .setSubscription(subscription)
                .setLimit(limit)
                .build();
        return Invoice.list(params).getData();
    }


    /**
     * Stripe will automatically send invoices to customers according to your subscriptions settings.
     * However, if you’d like to manually send an invoice to your customer out of the normal schedule,
     * you can do so. When sending invoices that have already been paid, there will be no reference to the payment in the email.
     *
     * @param id
     * @return
     */
    public Invoice sendInvoiceForManualPayment(String id) throws StripeException {
        Invoice resource = Invoice.retrieve(id);
        InvoiceSendInvoiceParams params = InvoiceSendInvoiceParams.builder().build();
        return resource.sendInvoice(params);
    }


    /**
     * Stripe automatically finalizes drafts before sending and attempting payment on invoices.
     * However, if you’d like to finalize a draft invoice manually, you can do so using this method.
     *
     * @param id
     * @param autoAdvance Controls whether Stripe performs <a href="https://docs.stripe.com/invoicing/integration/automatic-advancement-collection">automatic collection</a>  of the invoice. If false, the invoice’s state doesn’t automatically advance without an explicit action.
     * @return Returns an invoice object with status=open.
     * @throws StripeException
     */
    public Invoice finalizeInvoice(String id, Boolean autoAdvance) throws StripeException {
        Invoice resource = Invoice.retrieve(id);
        InvoiceFinalizeInvoiceParams params = InvoiceFinalizeInvoiceParams.builder()
                .setAutoAdvance(autoAdvance)
                .build();
        return resource.finalizeInvoice(params);
    }


    /**
     * Stripe automatically creates and then attempts to collect payment on invoices for
     * customers on subscriptions according to your subscriptions settings.
     * However, if you’d like to attempt payment on an invoice
     * out of the normal collection schedule or for some other reason, you can do so.
     *
     * @param id
     * @param forgive       if true, it will forgive the difference between the available amount on the source and the required amount
     * @param paymentMethod A PaymentMethod to be charged.
     *                      The PaymentMethod must be the ID of a PaymentMethod belonging
     *                      to the customer associated with the invoice being paid.
     * @return Returns the invoice object.
     * @throws StripeException
     */
    public Invoice payInvoice(String id, Boolean forgive, String paymentMethod) throws StripeException {
        Invoice invoice = Invoice.retrieve(id);

        InvoicePayParams params = InvoicePayParams.builder()
                .setForgive(forgive)
                .setPaymentMethod(paymentMethod)
                .build();

        return invoice.pay(params);
    }
}
