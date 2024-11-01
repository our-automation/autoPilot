package com.automation.utils.listeners;

import com.automation.mobileApp.AndroidDriverManagerUtil;
import com.automation.webutils.WebDriverManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.*;

/**
 * @author Maheswara
 * @created on 30/06/21
 */

@Component
public class BaseTestNGListener implements ITestListener, ISuiteListener {

    private static final WebDriverManagerUtil webDriverManagerUtil = new WebDriverManagerUtil();
    private static final AndroidDriverManagerUtil androidDriverManagerUtil= new AndroidDriverManagerUtil();

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onFinish(ISuite suite) {
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        webDriverManagerUtil.quitDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        webDriverManagerUtil.quitDriver();
        androidDriverManagerUtil.quitDriver();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        webDriverManagerUtil.quitDriver();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }
}
