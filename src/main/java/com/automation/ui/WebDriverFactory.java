package com.automation.ui;

import com.automation.utils.exceptions.UIException;
import com.automation.utils.listeners.BaseUIClass;
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

    public WebDriver getDriver() {
        return BaseUIClass.driver.get();
    }

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
}
