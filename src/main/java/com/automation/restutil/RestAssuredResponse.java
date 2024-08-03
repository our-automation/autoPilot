package com.automation.restutil;

import lombok.Data;

import java.util.Map;

@Data
public class RestAssuredResponse {
    private String body;
    private Long time;
    private Integer statusCode;
    private Map<String,Object> headers;

}
