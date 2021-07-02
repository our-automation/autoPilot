package com.automation.utils.listeners;

import com.automation.ui.DriverType;
import com.automation.ui.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.ITestContext;
import org.testng.annotations.*;

/**
 * @author Maheswara
 * @created on 30/06/21
 */

public class BaseUIClass extends BaseClass {

    @Autowired
    private WebDriverFactory webDriverFactory;
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void uiBeforeMethod(DriverType browser) {
        driver.set(webDriverFactory.createDriver(browser));

    }

    @AfterMethod(alwaysRun = true)
    public void uiAfterMethod() {
        webDriverFactory.getDriver().quit();
    }

    @AfterTest(alwaysRun = true)
    public void uiAfterTest() {
    }

    @AfterClass(alwaysRun = true)
    public void uiAfterClass(ITestContext iTestContext) throws Exception {
    }

    @AfterSuite(alwaysRun = true)
    public void uiAfterSuite() {

    }
}
