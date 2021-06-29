package com.automation.utils.extentreport;

import com.automation.utils.listeners.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class ExtentTestManager {
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports extent = ExtentManager.getExtent();

    public synchronized static ExtentTest getTest() {
        return extentTest.get();
    }

    public synchronized static ExtentTest createTest(String name, String testCaseType, String deviceId) {
        ExtentTest test = extent.createTest(name, testCaseType);

        if (deviceId != null && !deviceId.isEmpty())
            test.assignCategory(testCaseType);
        extentTest.set(test);
        return getTest();
    }

    public synchronized static ExtentTest createTest(String name, String testCaseType) {
        return createTest(name, testCaseType, String.valueOf(Thread.currentThread().getId()));
    }

    public static void info(String message) {
        BaseClass.parentTest.get().log(Status.INFO, message);
    }

    public static void pass(String message) {
        BaseClass.parentTest.get().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));

    }

    public static void fail(String message) {
        BaseClass.parentTest.get().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.PINK));

    }

    public static void fatal(String message) {
        BaseClass.parentTest.get().log(Status.FATAL, MarkupHelper.createLabel(message, ExtentColor.RED));

    }

    public static void error(String message) {
        BaseClass.parentTest.get().log(Status.ERROR, MarkupHelper.createLabel(message, ExtentColor.ORANGE));

    }

    public static void warining(String message) {
        BaseClass.parentTest.get().log(Status.WARNING, MarkupHelper.createLabel(message, ExtentColor.YELLOW));

    }

    public static void skip(String message) {
        BaseClass.parentTest.get().log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
    }
}
