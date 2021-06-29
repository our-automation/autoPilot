package com.automation.utils.listeners;

import com.automation.utils.config.ConfigManager;
import com.automation.utils.extentreport.ExtentManager;
import com.automation.utils.logger.ILogger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static ConfigManager configManager = new ConfigManager();
    static int maxRetryCount;

    static {
        try {
            maxRetryCount = configManager.getInt("retry.test.count");
        } catch (Exception ex) {
            maxRetryCount = 0;
        }
    }


    public boolean retry(ITestResult result) {

        if (!result.isSuccess()) {
            result.getTestContext().getSkippedTests().removeResult(result.getMethod());
            if (retryCount < maxRetryCount) {
                try {

                    ExtentManager.extent.removeTest(BaseClass.parentTest.get());
                } catch (Exception ex) {

                }
                retryCount++;
                ILogger.log.info("Retrying " + result.getName() + " with status "
                        + getResultStatusName(result.getStatus()) + " for the " + retryCount + " time(s).");

                return true;
            }
        }
        return false;
    }

    public String getResultStatusName(int status) {
        switch (status) {
            case -1:
                return "CREATED";
            case 1:
                return "SUCCESS";
            case 2:
                return "FAILURE";
            case 3:
                return "SKIP";
            case 4:
                return "SUCCESS_PERCENTAGE_FAILURE";
            case 16:
                return "STARTED";
            default:
                throw new ArithmeticException("status code not found " + status);
        }

    }
}
