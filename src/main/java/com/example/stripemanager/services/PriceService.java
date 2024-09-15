package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.PriceUpdateParams;
import com.stripe.param.ProductListParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PriceService {

    /**
     * Creates a new price for an existing product. The price can be recurring or one-time.
     * @param price
     * @return
     * @throws StripeException
     */
    public Price addPrice(Price price) throws StripeException {
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency(price.getCurrency())
                        .setUnitAmount(price.getUnitAmount())
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProduct(price.getProduct())
                        .build();

        return Price.create(params);
    }


    /**
     * Updates the specified price by setting the values of the parameters passed. Any parameters not provided are left unchanged.
     * @param id
     * @return
     * @throws StripeException
     */
    public Price updatePriceById(String id) throws StripeException {

        Price resource = Price.retrieve(id);

        PriceUpdateParams params =
                PriceUpdateParams.builder().putMetadata("order_id", "6735").build();

        return resource.update(params);
    }


    /**
     * Retrieves the price with the given ID.
     * @param id
     * @return
     * @throws StripeException
     */
    public Price getPriceById(String id) throws StripeException {
        return Price.retrieve(id);
    }


    /**
     * Returns a list of your active prices, excluding @see <a href="https://docs.stripe.com/products-prices/pricing-models#inline-pricing"> inline prices</a>. For the list of inactive prices, set active to false.
     * @param active
     * @param product
     * @param endingBefore
     * @param limit
     * @param startingAfter
     * @return
     * @throws StripeException
     */
    public List<Price> listPrices(
            Boolean active,
            String product,
            String endingBefore,
            Long limit,
            String startingAfter
    ) throws StripeException {


        PriceListParams params = PriceListParams.builder()
                .setLimit(limit)
                .setActive(active)
                .setProduct(product)
                .setStartingAfter(startingAfter)
                .setEndingBefore(endingBefore)
                .build();

        return Price.list(params).getData();
    }


}
