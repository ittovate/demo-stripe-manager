package com.example.stripemanager.services;


import com.example.stripemanager.utils.RestResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.WebhookEndpoint;
import com.stripe.net.Webhook;
import com.stripe.param.WebhookEndpointCreateParams;
import com.stripe.param.WebhookEndpointListParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookService {


    /**
     * A webhook endpoint must have a url and a list of enabled_events.
     * You may optionally specify the Boolean connect parameter.
     * If set to true, then a Connect webhook endpoint that notifies the specified url about events
     * from all connected accounts is created; otherwise an account webhook endpoint that notifies
     * the specified url only about events from your account is created.
     *
     * @param webhook
     * @return
     */
    public WebhookEndpoint createWebhook(WebhookEndpoint webhook) throws StripeException {

        int enabledEventsLength = webhook.getEnabledEvents().size();
        WebhookEndpointCreateParams.EnabledEvent enabledEvent = WebhookEndpointCreateParams.EnabledEvent.ALL;

        if (enabledEventsLength == 1) {
            System.out.println("came here why?");
            if (!webhook.getEnabledEvents().get(0).equals("*")) {
                enabledEvent = WebhookEndpointCreateParams.EnabledEvent.valueOf(webhook.getEnabledEvents().get(0));
            }
            webhook.setEnabledEvents(List.of(enabledEvent.name()));
        }
        webhook.getEnabledEvents().stream().forEach(item ->{
            System.out.println("printing item " + item);
        });

        WebhookEndpointCreateParams params = WebhookEndpointCreateParams.builder()
                .setUrl(webhook.getUrl())
                .addAllEnabledEvent(
                        webhook.getEnabledEvents().stream().map(WebhookEndpointCreateParams.EnabledEvent::valueOf).toList()
                )
                .setDescription(webhook.getDescription())
                .build();
        return WebhookEndpoint.create(params);
    }


    /**
     * Returns a list of your webhook endpoints.
     *
     * @return
     */
    public List<WebhookEndpoint> listWebhooks(
            String endingBefore,
            Long limit,
            String startingAfter
    ) throws StripeException {
        WebhookEndpointListParams params = WebhookEndpointListParams.builder()
                .setLimit(limit)
                .setEndingBefore(endingBefore)
                .setStartingAfter(startingAfter)
                .build();
        return WebhookEndpoint.list(params).getData();
    }

    /**
     * Retrieves the webhook endpoint with the given ID.
     *
     * @param id
     * @return
     */
    public WebhookEndpoint getWebhookById(String id) throws StripeException {
        return WebhookEndpoint.retrieve(id);
    }
}
