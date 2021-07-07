package com.automation.ui;

import com.automation.utils.listeners.BaseUIClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Maheswara
 * @created on 02/07/21
 */
@Component
public class Element {

    @Autowired
    private WebDriverFactory webDriverFactory;
    @Autowired
    private BaseUIClass baseUIClass;

    public void click(String locator) {
        baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).click();

    }

    public void senKey(String locator, String data) {
        baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).sendKeys(data);
    }

    public String getText(String locator) {
        return baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).getText();
    }

    public String getTagName(String locator) {
        return baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).getTagName();
    }

    public boolean isDisplayed(String locator) {
        return baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).isDisplayed();
    }

    public boolean isEnabled(String locator) {
        return baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).isEnabled();
    }

    public boolean isSelected(String locator) {
        return baseUIClass.getDriver().findElement(webDriverFactory.getLocator(locator)).isSelected();
    }

}
