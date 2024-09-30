package com.example.stripemanager.services;


import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.param.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    /**
     * Creates a new price for an existing product. The price can be recurring or one-time.
     *
     * @param price
     * @return
     * @throws StripeException
     */
    public Price addPrice(Price price) throws StripeException {
        Price.Recurring recurring = price.getRecurring();
        PriceCreateParams.Recurring.Interval interval;
        PriceCreateParams.Recurring createRecurringParams;
        if (recurring != null) {

            switch (recurring.getInterval()) {
                case "day":
                    interval = PriceCreateParams.Recurring.Interval.DAY;
                    break;
                case "month":
                    interval = PriceCreateParams.Recurring.Interval.MONTH;
                    break;
                case "week":
                    interval = PriceCreateParams.Recurring.Interval.WEEK;
                    break;
                default:
                    interval = PriceCreateParams.Recurring.Interval.YEAR;
                    break;

            }
            createRecurringParams = PriceCreateParams.Recurring.builder()
                    .setInterval(interval)
                    .build();
        } else {
            createRecurringParams = null;
        }

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency(price.getCurrency())
                        .setUnitAmount(price.getUnitAmount())
                        .setRecurring(
                                createRecurringParams
                        )
                        .setProduct(price.getProduct())
                        .build();

        return Price.create(params);
    }



    /**
     * Retrieves the price with the given ID.
     *
     * @param id
     * @return
     * @throws StripeException
     */
    public Price getPriceById(String id) throws StripeException {
        return Price.retrieve(id);
    }


    /**
     * Returns a list of your active prices, excluding @see <a href="https://docs.stripe.com/products-prices/pricing-models#inline-pricing"> inline prices</a>. For the list of inactive prices, set active to false.
     *
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
