package com.automation.restutil;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RequestBuilder {
    @Autowired
    private RestAssuredUtil restAssuredUtil;
    private static ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();

    public RequestBuilder() {
        requestSpecification.set(RestAssured.given().relaxedHTTPSValidation());
    }

    public RestAssuredResponse get(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.GET, url);
    }

    public RestAssuredResponse post(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.POST, url);
    }

    public RestAssuredResponse put(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.PUT, url);
    }

    public RestAssuredResponse delete(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.DELETE, url);
    }
    public RestAssuredResponse head(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.HEAD, url);
    }

    public RestAssuredResponse options(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.OPTIONS, url);
    }

    public RestAssuredResponse patch(String url) {
        return new RestAssuredUtil().response(requestSpecification.get(), HTTPMethod.PATCH, url);
    }

    public RequestBuilder header(String key, String value) {
        requestSpecification.get().header(key, value);
        return this;
    }

    public RequestBuilder headers(Map<String, String> headers) {
        requestSpecification.get().headers(headers);
        return this;
    }

    public RequestBuilder body(String body) {
        requestSpecification.get().body(body);
        return this;
    }

    public RequestBuilder body(Object body) {
        requestSpecification.get().body(body);
        return this;
    }

    public RequestBuilder params(Map<String, String> params) {
        requestSpecification.get().params(params);
        return this;
    }

    public RequestBuilder param(String key, String value) {
        requestSpecification.get().param(key, value);
        return this;
    }

    public RequestBuilder queryParams(Map<String, String> params) {
        requestSpecification.get().queryParams(params);
        return this;
    }

    public RequestBuilder queryParam(String key, String value) {
        requestSpecification.get().queryParam(key, value);
        return this;
    }


    public RequestBuilder formParams(Map<String, String> params) {
        requestSpecification.get().formParams(params);
        return this;
    }

    public RequestBuilder formParam(String key, String value) {
        requestSpecification.get().formParam(key, value);
        return this;
    }
}
