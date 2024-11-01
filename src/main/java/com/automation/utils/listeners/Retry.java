package com.automation.utils.listeners;

import com.automation.SystemProperties;
import com.automation.utils.file.PropertiesReader;
import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Objects;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
@Slf4j
public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static PropertiesReader propertiesReader = new PropertiesReader();
    static int maxRetryCount = Objects.nonNull(SystemProperties.RETRY_COUNT) ? Integer.parseInt(SystemProperties.RETRY_COUNT) :
            propertiesReader.getInt("retry.count");

    public boolean retry(ITestResult result) {

        if (!result.isSuccess()) {
            result.getTestContext().getSkippedTests().removeResult(result.getMethod());
            if (retryCount < maxRetryCount) {
                retryCount++;
                log.info("Retrying " + result.getName() + " with status "
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
