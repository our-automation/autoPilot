package com.automation;

import com.automation.restutil.RestAssuredResponse;
import com.automation.restutil.RestAssuredUtil;
import com.automation.utils.config.PropertiesReader;
import com.automation.utils.listeners.BaseClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class Sample {

    @Autowired
    PropertiesReader propertiesReader;
    @Test
    public void sampleTest(){
        propertiesReader.getString("header.file.path");
        RestAssuredResponse restAssuredResponse = RestAssuredUtil.sendRequest().get("https://jitpack.io/api/refs/com.github.mahe2421/automation-framework");

        System.out.printf(restAssuredResponse.getBody());
    }

    public static void main(String[] args) {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.quit();
    }
}
