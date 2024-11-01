package com.automation.cucumber;

import com.automation.webutils.WebDriverManagerUtil;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StepImplementation {
    @Autowired
    private WebDriverManagerUtil webDriverManagerUtil;

    public void launchBrowser(DataTable dataTable) {
        List<Map<String, String>> dataTableListMaps = dataTable.asMaps(String.class, String.class);
        Map<String, String> tableMap = new HashMap<>();
        dataTableListMaps.stream().map(map -> map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))).forEach(tableMap::putAll);

        WebDriver browser = webDriverManagerUtil.getDriver(tableMap.get("browser"));
        browser.get("https://qaautomation.expert/2022/08/15/pdf-extentreport-for-cucumber-and-testng/");

//        browser.quit();

    }

}
