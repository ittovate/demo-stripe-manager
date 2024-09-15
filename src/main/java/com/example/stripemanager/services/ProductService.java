package com.example.stripemanager.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.ProductListParams;
import com.stripe.param.ProductUpdateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    public ProductService(@Value("${stripe.api.secret-key}")
                          String apiKey) {
        Stripe.apiKey = apiKey;
    }

    /**
     * Creates a new product object.
     * @param product
     * @return
     * @throws StripeException
     */
    public Product addProduct(Product product) throws StripeException {

        ProductCreateParams params =
                ProductCreateParams.builder()
                        .setName(product.getName())
                        .setActive(product.getActive())
                        .setShippable(product.getShippable())
                        .setDescription(product.getDescription())
                        .build();

        return Product.create(params);
    }




    /**
     * Retrieves the details of an existing product.
     * Supply the unique product ID from either a product creation request or the product list,
     * and Stripe will return the corresponding product information.
     * @param id
     * @return the retrieved product
     * @throws StripeException
     */
    public Product getProductById(String id) throws StripeException {
        return Product.retrieve(id);
    }



    /**
     * Returns a list of your products. The products are returned sorted by creation date, with the most recently created products appearing first.
     *
     * @param active:        Only return products that are active or inactive (e.g., pass false to list all inactive products).
     * @param created:       Only return products that were created during the given date interval. (gt, gte, lt, lte)
     * @param endingBefore:  A cursor for use in pagination. ending_before is an object ID that defines your place in the list.
     *                       For instance, if you make a list request and receive 100 objects, starting with obj_bar,
     *                       your subsequent call can include ending_before=obj_bar in order to fetch the previous page of the list.
     * @param limit:         A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.
     * @param shippable:     Only return products that can be shipped (i.e., physical, not digital products).
     * @param startingAfter: A cursor for use in pagination. starting_after is an object ID that defines your place in the list.
     *                       For instance, if you make a list request and receive 100 objects, ending with obj_foo,
     *                       your subsequent call can include starting_after=obj_foo in order to fetch the next page of the list.
     * @param url:           Only return products with the given url.
     * @throws StripeException
     * @return: A Map with a data property that contains an array of up to limit products,
     * starting after product starting_after. Each entry in the array is a separate product object.
     * If no more products are available, the resulting array will be empty.
     */
    public List<Product> listProducts(
            Boolean active,
            Map<String, Long> created,
            String endingBefore,
            Long limit,
            Boolean shippable,
            String startingAfter,
            String url
    ) throws StripeException {

        ProductListParams.Created created1 = new ProductListParams.Created.Builder()
                .setGt(created.get("gt"))
                .setGte(created.get("gte"))
                .setLt(created.get("lt"))
                .setLte(created.get("lte"))
                .build();

        ProductListParams params = ProductListParams.builder()
                .setLimit(limit)
                .setActive(active)
                .setShippable(shippable)
                .setStartingAfter(startingAfter)
                .setUrl(url)
                .setEndingBefore(endingBefore)
                .setCreated(created1)
                .build();

        return Product.list(params).getData();
    }


}
