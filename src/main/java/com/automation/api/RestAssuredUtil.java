package com.automation.api;

import com.automation.utils.config.ConfigManager;
import com.automation.utils.exceptions.APIException;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
@Component
public class RestAssuredUtil {

    private static ConfigManager configManager = new ConfigManager();
    static {

        String baseUrl;
        if (System.getProperty("BASE_URL") != null) {
            baseUrl = System.getProperty("BASE_URL");
        } else {
            baseUrl = configManager.getString("base.url");
        }
        if (baseUrl==null||baseUrl.isEmpty()){
            throw new APIException("Base url can't be NULL, Please specify the value");
        }
        RestAssured.baseURI=baseUrl;
    }

    public RestAssuredResponse sendRequest(RequestBuilder builder, HTTPMethod httpMethod, String endPoint) {
        RequestSpecification build = builder.build();
        Response response = request(build, httpMethod, endPoint);
        return response(response);
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

    private RestAssuredResponse response(Response response) {

        RestAssuredResponse restAssuredResponse = new RestAssuredResponse();

        restAssuredResponse.setStatusCode(response.getStatusCode());
        restAssuredResponse.setBody(response.asString());
        restAssuredResponse.setTime(response.getTime());
        restAssuredResponse.setHeaders(response.getHeaders()
                .asList().stream().filter(Objects::nonNull).collect(Collectors.toMap(Header::getName, Header::getValue)));

        return restAssuredResponse;
    }
}
