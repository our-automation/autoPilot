package com.automation.restutils;

import lombok.Data;

import java.util.Map;

/**
 * @author Maheswara
 * @created on 04/08/24
 */

@Data
public class RestAssuredResponse {
    private String body;
    private Long time;
    private Integer statusCode;
    private Map<String, Object> headers;

}
