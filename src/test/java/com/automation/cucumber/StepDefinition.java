package com.automation.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefinition {
    @Autowired
    private StepImplementation stepImplementation;

    @Given("I launch browser")
    public void i_launch_browser(DataTable dataTable) {
        stepImplementation.launchBrowser(dataTable);
    }
}
