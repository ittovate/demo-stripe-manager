package com.example.stripemanager.utils;

import org.springframework.http.HttpStatusCode;

public class RestResponse<T>
{
    private T body;
    private String message;

    private HttpStatusCode statusCode;


    /**
     * @param statusCode
     */
    public RestResponse(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param body
     * @param statusCode
     */
    public RestResponse(T body, HttpStatusCode statusCode) {
        this.body = body;
        this.statusCode = statusCode;


    }
    /**
     * @param body
     * @param message
     * @param statusCode
     */
    public RestResponse(T body, String message, HttpStatusCode statusCode) {
//        super(body, statusCode);
        this.message = message;
        this.body = body;
        this.statusCode = statusCode;
    }


    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }


}
