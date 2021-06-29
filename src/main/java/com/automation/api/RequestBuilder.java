package com.automation.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class RequestBuilder {

    private RequestSpecification requestSpec;

    public RequestBuilder() {
        requestSpec = new RestAssured().given();
    }

    public RequestSpecification build() {
        return requestSpec;
    }

    public RequestBuilder header(String key, String value) {
        requestSpec.header(key, value);
        return this;
    }


    public RequestBuilder headers(Map<String, ?> headers) {
        requestSpec.headers(headers);
        return this;
    }


    //    request body
    public RequestBuilder body(String body) {

        requestSpec.body(body);
        return this;
    }

    public RequestBuilder body(Object body) {

        requestSpec.body(body);
        return this;
    }

    public RequestBuilder params(Map<String, ?> params) {
        requestSpec.params(params);
        return this;
    }

    public RequestBuilder formParam(Map<String, ?> formParams) {
        requestSpec.formParams(formParams);
        return this;
    }

    //    File upload
    public RequestBuilder multiPart(String controlName, File file) {
        requestSpec.multiPart(controlName, file);
        return this;
    }

    public RequestBuilder multiParts(Map<String, String> multiPart) {
        for (Map.Entry<String, String> entry : multiPart.entrySet()) {
            requestSpec.multiPart(entry.getKey(), entry.getValue());

        }
        return this;
    }
}
