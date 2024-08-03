package com.automation.utils.listeners;


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

    static {
        Logger.logger();
    }

}
