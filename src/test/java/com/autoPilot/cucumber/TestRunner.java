package com.autoPilot.cucumber;

import com.autoPilot.webutils.WebDriverManagerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

@CucumberOptions(features = {"/Users/maheswara/repo/automation-framework/src/test/resources/features/cucumber.feature"},
        glue = {"com.automation.cucumber"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Autowired
    private WebDriverManagerUtil webDriverManagerUtil;

    @After
    public void after(Scenario scenario) {
        addScreenshot(scenario);
    }

    public void addScreenshot(Scenario scenario) {
        File screenshot = ((TakesScreenshot) webDriverManagerUtil.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", scenario.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
