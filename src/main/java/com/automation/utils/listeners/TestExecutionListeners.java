package com.automation.utils.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.*;
import org.testng.collections.Maps;

import java.util.Map;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class TestExecutionListeners extends TestListenerAdapter implements IConfigurationListener, ITestListener {
    public static Map<String, Object> maps = Maps.newConcurrentMap();

    public void onConfigurationSuccess(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
    }

    public void onConfigurationFailure(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
    }

    public void onConfigurationSkip(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
        BaseClass.parentTest.get().pass(MarkupHelper.createLabel("<b><font color=green>" +
                itr.getName() + " - TEST CASE SUCCESS" + "<br>", ExtentColor.WHITE));
    }

    @Override
    public void onTestFailure(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
        BaseClass.parentTest.get().fail(MarkupHelper.createLabel("<b><font color=red>" +
                itr.getName() + "  test failed with below reason <br><b>" +
                itr.getThrowable().getMessage() + "<br>", ExtentColor.WHITE));

    }

    @Override
    public void onTestSkipped(ITestResult itr) {
        maps.put(itr.getMethod().getMethodName(), itr.getInstance());
        BaseClass.parentTest.get().skip(MarkupHelper.createLabel("<b><font color=orange>" +
                itr.getName() + "  test skiped with below reason <br> <b>" +
                itr.getThrowable().getMessage() + "<br>", ExtentColor.WHITE));
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
