package com.autoPilot.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
@Slf4j
public class Hooks {

}
