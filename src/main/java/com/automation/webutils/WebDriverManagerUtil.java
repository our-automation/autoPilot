package com.automation.webutils;

import com.automation.utils.config.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Maheswara
 * @created on 04/08/24
 */

@Component
public class WebDriverManagerUtil {
    @Autowired
    PropertiesReader propertiesReader;
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public WebDriver getDriver() {
        return getDriver(propertiesReader.getString("browser.name"), null);
    }

    public WebDriver getDriver(String browser) {
        return getDriver(browser, null);
    }

    public WebDriver getDriver(String browser, String version) {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        BrowserType browserType = BrowserType.valueOf(browser);
        switch (browserType) {
            case chrome:
                webDriverThreadLocal.set(getChromeDriver(version, null));
                break;
            case headlesschrome:
                webDriverThreadLocal.set(getChromeDriver(version, "headless"));
                break;
            case firefox:
                webDriverThreadLocal.set(getFirefoxDriver(version));
                break;
            case ie:
                webDriverThreadLocal.set(getIEDriver(version));
                break;
            case edge:
                webDriverThreadLocal.set(getEdgeDriver(version));
                break;
            case safari:
                webDriverThreadLocal.set(getSafariDriver(version));
                break;
        }
        webDriverThreadLocal.get().manage().window().maximize();
        webDriverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(propertiesReader.getLong("implicitly.wait")));
        return webDriverThreadLocal.get();
    }

    private WebDriver getChromeDriver(String version, String mode) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Objects.nonNull(version)) {
            chromeOptions.setBrowserVersion(version);
        }
        if (Objects.nonNull(mode) && mode.equals("headless")) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("window-size=1920,1080");
        }
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver getFirefoxDriver(String version) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (Objects.nonNull(version)) {
            firefoxOptions.setBrowserVersion(version);
        }
        return new FirefoxDriver(firefoxOptions);
    }

    private WebDriver getIEDriver(String version) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        if (Objects.nonNull(version)) {
            internetExplorerOptions.setBrowserVersion(version);
        }
        return new InternetExplorerDriver(internetExplorerOptions);
    }

    private WebDriver getEdgeDriver(String version) {
        EdgeOptions edgeOptions = new EdgeOptions();
        if (Objects.nonNull(version)) {
            edgeOptions.setBrowserVersion(version);
        }
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("start-maximized");
        return new EdgeDriver(edgeOptions);
    }

    private WebDriver getSafariDriver(String version) {
        SafariOptions safariOptions = new SafariOptions();
        if (Objects.nonNull(version)) {
            safariOptions.setBrowserVersion(version);
        }
        return new SafariDriver(safariOptions);
    }

    protected WebElement fluentWait(LocatorType locatorType, String locator) {
        Wait<WebDriver> wait = new FluentWait<>(webDriverThreadLocal.get())
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
}
