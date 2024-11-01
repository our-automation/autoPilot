package com.automation.mobileApp;

import com.automation.SystemProperties;
import com.automation.utils.file.PropertiesReader;
import com.automation.webutils.LocatorType;
import com.automation.webutils.SelectBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class AndroidDriverManagerUtil {
    @Autowired
    private PropertiesReader propertiesReader;
    private static ThreadLocal<WebDriver> androidDriverThreadLocal = new ThreadLocal<>();


    public void click(LocatorType locatorType, String locator) {
        waitForElementToBeClickable(locatorType, locator);
        fluentWait(locatorType, locator).click();
    }

    private void waitForElementToBeClickable(LocatorType locatorType, String locator) {
        WebDriverWait webDriverWait = new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(propertiesReader.getLong("fluent.wait.timeout")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(fluentWait(locatorType, locator)));
    }

    public void sendkeysByclear(LocatorType locatorType, String locator, String text) {
        fluentWait(locatorType, locator).clear();
        sendkeys(locatorType, locator, text);
    }

    public void sendkeys(LocatorType locatorType, String locator, String text) {
        fluentWait(locatorType, locator).sendKeys(text);
    }

    public Boolean isDisplayed(LocatorType locatorType, String locator) {
        return fluentWait(locatorType, locator).isDisplayed();
    }

    public Boolean isSelected(LocatorType locatorType, String locator) {
        return fluentWait(locatorType, locator).isSelected();
    }

    public Boolean isEnabled(LocatorType locatorType, String locator) {
        return fluentWait(locatorType, locator).isEnabled();
    }

    public String getText(LocatorType locatorType, String locator) {
        return fluentWait(locatorType, locator).getText();
    }

    public void isElementVisable(LocatorType locatorType, String locator, int waitTimeInSec) {
        WebDriverWait webDriverWait = new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(waitTimeInSec));
        webDriverWait.until(ExpectedConditions.visibilityOf(fluentWait(locatorType, locator)));
    }

    public void isElementVisable(LocatorType locatorType, String locator) {
        WebDriverWait webDriverWait = new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(Integer.parseInt(propertiesReader.getString("fluent.wait.timeout"))));
        webDriverWait.until(ExpectedConditions.visibilityOf(fluentWait(locatorType, locator)));
    }

    public void selectDropDown(LocatorType locatorType, String locator, SelectBy selectBy, String value) {
        WebElement element = fluentWait(locatorType, locator);
        Select select = new Select(element);
        switch (selectBy) {
            case INDEX:
                select.selectByIndex(Integer.parseInt(value));
                break;
            case VALUE:
                select.selectByValue(value);
                break;
            case VISIBLE_TEXT:
                select.selectByVisibleText(value);
                break;
        }
    }

    public void javascriptExecutor(LocatorType locatorType, String locator, String scriptValue) {
        ((JavascriptExecutor) fluentWait(locatorType, locator)).executeScript(scriptValue);
    }

    public void doKeyBoardEnter() {
//        getAndroidDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public AndroidDriver getAndroidDriver() {
        Map<String, String> capabilities = new HashMap<>();
        capabilities.put("automationName", "uiautomator2");
        capabilities.put("deviceName", Objects.nonNull(SystemProperties.DEVICE_NAME) ? SystemProperties.DEVICE_NAME : propertiesReader.getString("device.name"));
        capabilities.put("browserName", Objects.nonNull(SystemProperties.BROWSER_NAME) ? SystemProperties.BROWSER_NAME : propertiesReader.getString("browser.name"));
        return getAndroidDriver(capabilities);
    }

    public AndroidDriver getAndroidDriver(Map<String, String> capabilitiesMap) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilitiesMap.forEach((key, value) -> capabilities.setCapability(key, value));
            String appiumUrl = Objects.nonNull(SystemProperties.APPIUM_URL) ? SystemProperties.APPIUM_URL : propertiesReader.getString("appium.url");
            return new AndroidDriver(new URL(appiumUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    protected WebElement fluentWait(LocatorType locatorType, String locator) {
        Wait<WebDriver> wait = new FluentWait<>(androidDriverThreadLocal.get())
                .withTimeout(Duration.ofSeconds(propertiesReader.getLong("fluent.wait.timeout")))
                .pollingEvery(Duration.ofMillis(propertiesReader.getLong("fluent.wait.polling.time")))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(webLocator(locatorType, locator)));
    }

    private By webLocator(LocatorType locatorType, String locator) {
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
            case PARTIAL_TEXT:
                return By.partialLinkText(locator);
            case TAG:
                return By.tagName(locator);
        }
        return null;
    }

    public void quitDriver() {
        try {
            if (androidDriverThreadLocal.get() != null) {
                androidDriverThreadLocal.get().quit();
                androidDriverThreadLocal.remove();
            }
        } catch (Exception ex) {

        }
    }
}
