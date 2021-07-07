package com.automation.ui;


import com.automation.utils.listeners.BaseUIClass;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.concurrent.TimeUnit;


import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author Maheswara
 * @created on 01/07/21
 */
public class WebTest1 extends BaseUIClass {
    @Autowired
    private Element element;

    @Test
    public void sample1() throws Exception {
        // Create object of DesiredCapabilities class
        AndroidDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
// Optional
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
// Specify the device name (any name)
        capabilities.setCapability("deviceName", "My New Phone");
// Platform version
        capabilities.setCapability("platformVersion", "9");
// platform name
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName","UiAutomator2");
// specify the application package that we copied from appium
        capabilities.setCapability("appPackage", "com.avail.easyloans.android");
// specify the application activity that we copied from appium
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"android");
        capabilities.setCapability("appActivity", "com.avail.easyloans.android.activities.ActivitySplash");
// Start android driver I used 4727 port by default it will be 4723
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
// Specify the implicit wait of 5 second
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
// Enter the text in textbox
        try {
//            driver.findElement(By.id("//android.widget.EditText[@content-desc='my_text_fieldCD']")).sendKeys("Mukesh Selenium Appium");
// click on registration button
            driver.findElement(By.id("@id/tv_name")).click();
        }catch (Exception e){
            System.out.println("error");

        }
// Wait for 10 second
        Thread.sleep(10000);
// close the application
        driver.quit();
    }


    @Test
    public void sample2() throws Exception {

        // Create object of DesiredCapabilities class

        AndroidDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
// Optional
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
// Specify the device name (any name)
        capabilities.setCapability("deviceName", "My New Phone");
// Platform version
        capabilities.setCapability("platformVersion", "9");
// platform name
        capabilities.setCapability("platformName", "Android");
// specify the application package that we copied from appium
        capabilities.setCapability("appPackage", "io.selendroid.testapp");
// specify the application activity that we copied from appium
        capabilities.setCapability("appActivity", ".HomeScreenActivity");
// Start android driver I used 4727 port by default it will be 4723
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
// Specify the implicit wait of 5 second
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
// Enter the text in textbox
        driver.findElement(By.xpath("//android.widget.EditText[@content-desc='my_text_fieldCD']")).sendKeys("Mukesh Selenium Appium");
// click on registration button
        driver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration")).click();
// Wait for 10 second
        Thread.sleep(10000);
// close the application
        driver.quit();
    }

}
