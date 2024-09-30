package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PaymentLinkListParams;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentLinkService {

    /**
     * Creates a payment link.
     * @param paymentLink <a href="https://docs.stripe.com/api/payment-link/object?lang=java">The Payment Link object</a>
     * @return the payment link.
     * @throws StripeException
     */
    public PaymentLink creatPaymentLink(PaymentLink paymentLink) throws StripeException {
        PaymentLinkCreateParams.Restrictions restrictions = PaymentLinkCreateParams.Restrictions.builder()
                .setCompletedSessions(
                        PaymentLinkCreateParams.Restrictions.CompletedSessions.builder()
                                .setLimit(paymentLink.getRestrictions().getCompletedSessions().getLimit())
                                .build()
                ).build();

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .setAllowPromotionCodes(paymentLink.getAllowPromotionCodes())
                        .setCurrency(paymentLink.getCurrency())
                        .setRestrictions(restrictions)
                        .addLineItem(PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(paymentLink.getLineItems().getData().get(0).getPrice().getId())
                                .setQuantity(paymentLink.getLineItems().getData().get(0).getQuantity())
                                .build())
                        .build();

        return PaymentLink.create(params);
    }


    /**
     * Returns a list of your payment links.
     * @param active: Only return payment links that are active or inactive (e.g., pass false to list all inactive payment links).
     *
     * @param endingBefore: A cursor for use in pagination. ending_before is an object ID that defines your place in the list.
     *                    For instance, if you make a list request and receive 100 objects, starting with obj_bar,
     *                    your subsequent call can include ending_before=obj_bar in order to fetch the previous page of the list.
     *
     * @param limit: A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.
     *
     * @param startingAfter: A cursor for use in pagination. starting_after is an object ID that defines your place in the list.
     *                     For instance, if you make a list request and receive 100 objects, ending with obj_foo,
     *                     your subsequent call can include starting_after=obj_foo in order to fetch the next page of the list.
     * @return A List of Payment Links in the data property that contains an array of up to limit payment links,
     *                      starting after payment link starting_after. Each entry in the array is a separate payment link object.
     *                      If no more payment links are available, the resulting array will be empty. This request should never throw an error.
     * @throws StripeException
     */
    public List<PaymentLink> listPaymentLinks(
            Boolean active,
            String endingBefore,
            Long limit,
            String startingAfter

    ) throws StripeException {
        PaymentLinkListParams params = PaymentLinkListParams.builder()
                .setActive(active)
                .setEndingBefore(endingBefore)
                .setLimit(limit)
                .setStartingAfter(startingAfter)
                .build();
        return PaymentLink.list(params).getData();
    }


    /**
     * Retrieve a payment link.
     * @param id: Id of the payment link to retrieve
     * @return: A Payment Link Object
     * @throws StripeException
     */
    public PaymentLink getPaymentLinkById(String id) throws StripeException {
        return PaymentLink.retrieve(id);
    }


}
