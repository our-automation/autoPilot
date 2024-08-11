package com.automation.webutils;

import com.automation.utils.config.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author Maheswara
 * @created on 04/08/24
 */

@Component
public class BrowserOperation {
    @Autowired
    private WebDriverManagerUtil webDriverManagerUtil;
    @Autowired
    private PropertiesReader propertiesReader;
    public void click(LocatorType locatorType, String locator) {
        waitForElementToBeClickable(locatorType, locator);
        webDriverManagerUtil.fluentWait(locatorType, locator).click();
    }

    private void waitForElementToBeClickable(LocatorType locatorType, String locator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriverManagerUtil.getDriver(), Duration.ofSeconds(propertiesReader.getLong("fluent.wait.timeout")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webDriverManagerUtil.fluentWait(locatorType, locator)));
    }

    public void sendkeysByclear(LocatorType locatorType, String locator, String text) {
        webDriverManagerUtil.fluentWait(locatorType, locator).clear();
        sendkeys(locatorType, locator, text);

    }

    public void sendkeys(LocatorType locatorType, String locator, String text) {
        webDriverManagerUtil.fluentWait(locatorType, locator).sendKeys(text);
    }
}
