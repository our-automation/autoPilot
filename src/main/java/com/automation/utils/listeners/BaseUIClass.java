//package com.automation.utils.listeners;
//
//import org.openqa.selenium.WebDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.testng.ITestContext;
//import org.testng.annotations.*;
//
///**
// * @author Maheswara
// * @created on 30/06/21
// */
//
//@Component
//public class BaseUIClass extends BaseClass {
//
//    @Autowired
//    private WebDriverFactory webDriverFactory;
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public WebDriver getDriver() {
//        return driver.get();
//    }
//
//    @BeforeMethod(alwaysRun = true)
//    @Parameters({"browser"})
//    public void uiBeforeMethod(DriverType browser) {
////        driver.set(webDriverFactory.createDriver(browser));
////        getDriver().get("https://uat.availfinance.in/");
//
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void uiAfterMethod() {
////        getDriver().quit();
//    }
//
//}
