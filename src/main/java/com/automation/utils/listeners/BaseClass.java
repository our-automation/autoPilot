package com.automation.utils.listeners;

import com.automation.utils.extentreport.ExtentManager;
import com.automation.utils.extentreport.ExtentTestManager;
import com.automation.utils.headers.HeadersUtil;
import com.automation.utils.logger.ILogger;
import com.automation.utils.logger.Logger;
import com.aventstack.extentreports.ExtentTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class BaseClass extends AbstractTestNGSpringContextTests implements ILogger {
    public static final Map<String, Map<String, String>> headers = HeadersUtil.getAllHeaders();

    static {
        Logger.logger();
    }

    public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    public ExtentTest parent;

    @BeforeSuite(alwaysRun = true)
    public void baseBeforeSuite() {

    }

    @BeforeClass(alwaysRun = true)
    public void baseBeforeClass(ITestContext iTestContext) throws Exception {
        parent = ExtentTestManager.createTest(getClass().getSimpleName(), iTestContext.getCurrentXmlTest().getName());
        log.info("execution class is " + getClass().getSimpleName());
    }

    @BeforeMethod(alwaysRun = true)
    public void baseBeforeMethod(Method method, ITestContext iTestContext) {

        parentTest.set(parent);
        ExtentTest child = parentTest.get().createNode(method.getName());
        parentTest.set(child);
        log.info("execution test case is " + method.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void baseAfterMethod(ITestResult result) {

        ExtentManager.getExtent().flush();
    }

    @AfterSuite(alwaysRun = true)
    public void baseAfterSuite() {

    }
}
