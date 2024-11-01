package com.automation.cucumber;

import com.automation.webutils.WebDriverManagerUtil;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

@CucumberContextConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Slf4j
public class Hooks {

}
