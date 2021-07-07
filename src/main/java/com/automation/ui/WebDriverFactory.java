package com.automation.ui;

import com.automation.utils.exceptions.UIException;
import com.automation.utils.listeners.BaseUIClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Maheswara
 * @created on 30/06/21
 */
@Component
public class WebDriverFactory {

    @Autowired
    private DriverFactory driverFactory;

    public WebDriver createDriver(DriverType driverType) {
        switch (driverType) {
            case CHROME:
                return driverFactory.getChromeDeriver();
            case FIREFOX:
                return driverFactory.getFirefoxDeriver();
            case IE:
                return driverFactory.getIEDriver();
            case SAFARI:
                return driverFactory.getSafariDriver();
            default:
                throw new UIException("Driver type not found with " + driverType);
        }
    }

    public By getLocator(String locator) {
        String[] split = locator.split(".");
        LocatorType locatorType = LocatorType.valueOf(split[split.length-1]);
        switch (locatorType) {
            case ID:
                return By.id(locator);
            case NAME:
                return By.name(locator);
            case XPATH:
                return By.xpath(locator);
            case CSS:
                return By.cssSelector(locator);
            case CLASS:
                return By.className(locator);
            case LINK_TEXT:
                return By.linkText(locator);
            case PARTIAL_LINK:
                return By.partialLinkText(locator);
            case TAG:
                return By.tagName(locator);
            default:
                throw new UIException("locator type not found with " + locatorType);
        }
    }

}
