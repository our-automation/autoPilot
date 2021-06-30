package com.automation;

import com.automation.api.HTTPMethod;
import com.automation.api.RequestBuilder;
import com.automation.api.RestAssuredResponse;
import com.automation.api.RestAssuredUtil;
import com.automation.utils.listeners.BaseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class ApiTest extends BaseClass {

    @Autowired
    private RestAssuredUtil restUtil;

    @Test(groups = {"endtoend"})
    public void PostTest() {
        RequestBuilder builder = new RequestBuilder()
                .headers(headers.get("products"))
                .body("request body");
        RestAssuredResponse restAssuredResponse = restUtil.sendRequest(builder, HTTPMethod.POST, "api end poing");
    }


    @Test(groups = {"endtoend"})
    public void getApiTest() {

        RequestBuilder requestBuilder = new RequestBuilder()
                .headers(headers.get("user_info"));

        RestAssuredResponse restAssuredResponse = restUtil.sendRequest(requestBuilder, HTTPMethod.GET, "api end poing");

    }

    @Test(groups = {"regression"})
    public void regression1(){

//        Assert.assertEquals(true,false);
    }

    @Test(groups = {"regression"})
    public void regression2(){

        throw new SkipException("test");

    }
    @Test(groups = {"regression"})
    public void regression3(){

    }


    @Test(groups = {"endtoend"})
    public void getApiTestFailedCase(){

        RequestBuilder requestBuilder = new RequestBuilder()
                .headers(headers.get("user_info"));

        RestAssuredResponse restAssuredResponse = restUtil.sendRequest(requestBuilder, HTTPMethod.GET, "api end poing");
//        Assert.assertEquals(true,false);

    }


}
