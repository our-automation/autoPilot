package com.automation;

import com.automation.restutils.RestAssuredResponse;
import com.automation.restutils.RestAssuredUtil;
import com.automation.utils.config.PropertiesReader;
import com.automation.utils.listeners.BaseClass;
import com.automation.webutils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class Sample  extends BaseClass {

    @Autowired
    PropertiesReader propertiesReader;
    @Autowired
    WebDriverManagerUtil webDriverManagerUtil;
    @Test
    public void sampleTest(){
        WebDriver chrome = webDriverManagerUtil.getDriver("firefox");
        chrome.quit();

    }

}
