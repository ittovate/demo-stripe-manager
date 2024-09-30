package com.example.stripemanager.dto;

import java.util.List;
import java.util.Map;

public class PaymentLinkDTO {

    private String id;
    private String object;
    private boolean active;
    private boolean allowPromotionCodes;
    private Long applicationFeeAmount;
    private Double applicationFeePercent;
    private String billingAddressCollection;
    private String currency;
    private String customerCreation;
    private boolean livemode;
    private String onBehalfOf;
    private String paymentMethodCollection;
    private String submitType;
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAllowPromotionCodes() {
        return allowPromotionCodes;
    }

    public void setAllowPromotionCodes(boolean allowPromotionCodes) {
        this.allowPromotionCodes = allowPromotionCodes;
    }

    public Long getApplicationFeeAmount() {
        return applicationFeeAmount;
    }

    public void setApplicationFeeAmount(Long applicationFeeAmount) {
        this.applicationFeeAmount = applicationFeeAmount;
    }

    public Double getApplicationFeePercent() {
        return applicationFeePercent;
    }

    public void setApplicationFeePercent(Double applicationFeePercent) {
        this.applicationFeePercent = applicationFeePercent;
    }

    public String getBillingAddressCollection() {
        return billingAddressCollection;
    }

    public void setBillingAddressCollection(String billingAddressCollection) {
        this.billingAddressCollection = billingAddressCollection;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerCreation() {
        return customerCreation;
    }

    public void setCustomerCreation(String customerCreation) {
        this.customerCreation = customerCreation;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public void setOnBehalfOf(String onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public String getPaymentMethodCollection() {
        return paymentMethodCollection;
    }

    public void setPaymentMethodCollection(String paymentMethodCollection) {
        this.paymentMethodCollection = paymentMethodCollection;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
