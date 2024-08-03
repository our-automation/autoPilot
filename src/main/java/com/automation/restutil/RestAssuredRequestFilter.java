package com.automation.restutil;

import com.automation.utils.logger.ILogger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class RestAssuredRequestFilter implements Filter, ILogger {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        String data = "\n\n======================================  Request data  ======================================" +
                "\nRequest Method =>  " + requestSpec.getMethod()
                + "\nRequest url => " + requestSpec.getURI()
                + "\nPath Params =>  " + requestSpec.getPathParams()
                + "\nQuery Params =>  " + requestSpec.getQueryParams()
                + "\nForm Params =>  " + requestSpec.getFormParams()
                + "\nHeaders => " + requestSpec.getHeaders()
                + "\nRequest body\n" + requestSpec.getBody()

                + "\n\n======================================  Response data  ======================================"
                + "\nStatus Code =>  " + response.getStatusCode()
                + "\nStatus Line =>  " + response.getStatusLine()
//                +"\nHeaders => "+response.getHeaders()
                + "\nresponse body => \n" + response.getBody().prettyPrint();
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            log.info(data);
        } else {
            log.error(data);
        }
        return response;
    }
}