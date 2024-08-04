package com.automation.restutils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Maheswara
 * @created on 04/08/24
 */

@Component
public class RestAssuredUtil {

    public static RequestBuilder sendRequest() {
        return new RequestBuilder();
    }

    public RestAssuredResponse response(RequestSpecification requestSpecBuilder, HTTPMethod httpMethod, String url) {
        Response response = request(requestSpecBuilder, httpMethod, url);

        RestAssuredResponse restAssuredResponse = new RestAssuredResponse();
        restAssuredResponse.setBody(response.asString());
        restAssuredResponse.setTime(response.getTime());
        restAssuredResponse.setStatusCode(response.getStatusCode());
        restAssuredResponse.setHeaders(response.getHeaders().asList().stream().filter(Objects::nonNull).collect(Collectors.toMap(Header::getName, Header::getValue)));

        return restAssuredResponse;
    }

    private Response request(RequestSpecification request, HTTPMethod requestType, String endPoint) {
        switch (requestType) {
            case GET:
                return request.filter(new RestAssuredRequestFilter()).get(endPoint);
            case POST:
                return request.filter(new RestAssuredRequestFilter()).post(endPoint);
            case PUT:
                return request.filter(new RestAssuredRequestFilter()).put(endPoint);
            case HEAD:
                return request.filter(new RestAssuredRequestFilter()).head(endPoint);
            case DELETE:
                return request.filter(new RestAssuredRequestFilter()).delete(endPoint);
            case OPTIONS:
                return request.filter(new RestAssuredRequestFilter()).options(endPoint);
            case PATCH:
                return request.filter(new RestAssuredRequestFilter()).patch(endPoint);
            default:
                throw new IllegalStateException("Unexpected value: " + requestType);
        }
    }
}
