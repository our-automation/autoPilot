package com.automation.utils.extentreport;

import com.automation.utils.config.ConfigManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class ExtentManager {
    private static ConfigManager configManager = new ConfigManager();
    public static ExtentReports extent;

    public synchronized static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter());
        }

        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        String path = configManager.getString("extent.report.path");
        if (path==null){
            path="./build/reports/tests/test/extent.html";
        }
        String title = configManager.getString("extent.document.title");
        if (title==null){
            title="Extent Report";
        }
        String name = configManager.getString("extent.report.name");
        if (name==null){
            name="Extent Report";
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);

        // report title
        htmlReporter.config().setDocumentTitle(title);
        htmlReporter.config().setReportName(name);
        htmlReporter.config().setTheme(Theme.STANDARD);
        return htmlReporter;
    }
}
