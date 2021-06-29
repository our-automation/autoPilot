package com.automation.api;

import lombok.Data;

import java.util.Map;

/**
 * @author Maheswara
 * @created on 28/06/21
 */

@Data
public class RestAssuredResponse {
    private String body;
    private long time;
    private int statusCode;
    private Map<String, String> headers;

}
