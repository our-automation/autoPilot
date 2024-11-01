package com.automation.testNG;

import com.automation.utils.file.PropertiesReader;
import com.automation.utils.listeners.RunWithTestNG;
import com.automation.webutils.LocatorType;
import com.automation.webutils.WebDriverManagerUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

@Slf4j
public class Sample extends RunWithTestNG {

    @Autowired
    PropertiesReader propertiesReader;
    @Autowired
    WebDriverManagerUtil webDriverManagerUtil;


//    @Test
    public void sampleTest() {
        log.info("test");
        WebDriver chrome = webDriverManagerUtil.getDriver("chrome");
        chrome.get("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox");
        webDriverManagerUtil.sendkeys(LocatorType.ID, "identifierId", "mahe.ame@gmail.com");
        webDriverManagerUtil.click(LocatorType.XPATH, "//span[text()='Next']");


    }

}
