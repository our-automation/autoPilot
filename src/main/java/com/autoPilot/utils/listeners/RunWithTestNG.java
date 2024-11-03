package com.autoPilot.utils.listeners;


import com.autoPilot.SystemProperties;
import com.autoPilot.utils.logger.ILogger;
import com.autoPilot.utils.logger.Logger;
import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteClassListenerAdapter;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Listeners({ExtentIReporterSuiteClassListenerAdapter.class})
public class RunWithTestNG extends AbstractTestNGSpringContextTests implements ILogger {

    static {
        Logger.logger();
        try {
            File file = new File(SystemProperties.USER_DIR + "/Screenshots");
            if (file.exists()) {
                FileUtils.forceDelete(new File("Screenshots"));
            }
            file = new File(SystemProperties.USER_DIR + "/Reports");
            if (file.exists()) {
                FileUtils.forceDelete(new File("Reports"));
            }
            FileUtils.forceMkdir(new File("Screenshots"));
            FileUtils.forceMkdir(new File("Reports"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
