package com.autoPilot.cucumber;

import com.autoPilot.webutils.WebDriverManagerUtil;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StepImplementation {
    @Autowired
    private WebDriverManagerUtil webDriverManagerUtil;

    public void launchBrowser(DataTable dataTable)  {
        List<Map<String, String>> dataTableListMaps = dataTable.asMaps(String.class, String.class);
        Map<String, String> tableMap = new HashMap<>();
        dataTableListMaps.stream().map(map -> map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))).forEach(tableMap::putAll);
        webDriverManagerUtil.getDriver();
        webDriverManagerUtil.get("https://passbook.epfindia.gov.in/MemberPassBook/login");
        File screenshot = ((TakesScreenshot) webDriverManagerUtil.getDriver()).getScreenshotAs(OutputType.FILE);
        File captchaElement = ((TakesScreenshot) webDriverManagerUtil.getDriver().findElement(By.id("captcha_id"))).getScreenshotAs(OutputType.FILE);

        String captchaImagePath = "captcha.png";
        try {
            FileUtils.copyFile(captchaElement, new File(captchaImagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialize Tesseract
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("path/to/tessdata"); // Update with the path to your tessdata folder
//        tesseract.setLanguage("eng");
//        try {
//            String s = tesseract.doOCR(new File(captchaImagePath));
//            System.out.println("captche data : "+s);
//        } catch (TesseractException e) {
//            throw new RuntimeException(e);
//        }


    }

}
