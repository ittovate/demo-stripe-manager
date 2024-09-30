package com.example.stripemanager.services;

import com.example.stripemanager.dto.ProductDTO;
import com.example.stripemanager.dto.ProductResponseDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.ProductListParams;
import com.stripe.param.ProductUpdateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.ArrayList;
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

    public ProductResponseDTO addProduct(ProductDTO product) throws StripeException {

        ProductCreateParams params =
                ProductCreateParams.builder()
                        .setName(product.getName())
                        .setActive(product.getActive())
                        .setDescription(product.getDescription())
                        .build();

        Product createdProduct =  Product.create(params) ;


        ProductResponseDTO responseDto = new ProductResponseDTO();
        responseDto.setActive(createdProduct.getActive());
        responseDto.setLivemode(createdProduct.getLivemode());
        responseDto.setName(createdProduct.getName());
        responseDto.setUpdated(createdProduct.getUpdated());
        responseDto.setType(createdProduct.getType());

        return responseDto ;
    }




    /**
     * Retrieves the details of an existing product.
     * Supply the unique product ID from either a product creation request or the product list,
     * and Stripe will return the corresponding product information.
     * @param id
     * @return the retrieved product
     * @throws StripeException
     */

    public ProductResponseDTO getProductById(String id) throws StripeException {
        Product  product = Product.retrieve(id);

        ProductResponseDTO responseDto = new ProductResponseDTO();
        responseDto.setId(product.getId());
        responseDto.setActive(product.getActive());
        responseDto.setLivemode(product.getLivemode());
        responseDto.setName(product.getName());
        responseDto.setUpdated(product.getUpdated());
        responseDto.setType(product.getType());

        return responseDto;

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

    public List<ProductResponseDTO> listProducts(
            Boolean active,
            Map<String, Long> created,
            String endingBefore,
            Long limit,
            Boolean shippable,
            String startingAfter,
            String url
    ) throws StripeException {
        ProductListParams.Builder paramsBuilder = ProductListParams.builder()
                .setLimit(limit)
                .setActive(active)
                .setShippable(shippable)
                .setStartingAfter(startingAfter)
                .setUrl(url)
                .setEndingBefore(endingBefore);


            ProductListParams.Created created1 = new ProductListParams.Created.Builder()
                    .setGt(created.get("gt"))
                    .setGte(created.get("gte"))
                    .setLt(created.get("lt"))
                    .setLte(created.get("lte"))
                    .build();
            paramsBuilder.setCreated(created1);


        ProductListParams params = paramsBuilder.build();

        ProductCollection productCollection = Product.list(params);
        List<Product> productList = productCollection.getData();

        List<ProductResponseDTO> productResponseList = new ArrayList<>();

        for (Product product : productList) {
            ProductResponseDTO responseDto = new ProductResponseDTO();
            responseDto.setActive(product.getActive());
            responseDto.setLivemode(product.getLivemode());
            responseDto.setName(product.getName());
            responseDto.setUpdated(product.getUpdated());
            responseDto.setType(product.getType());
            responseDto.setId(product.getId());
            productResponseList.add(responseDto);
        }

        return productResponseList;
    }


}
