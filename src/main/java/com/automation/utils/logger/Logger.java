package com.automation.utils.logger;

import org.apache.log4j.PropertyConfigurator;

import java.net.URL;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class Logger {
    public static void logger() {
        URL resource = Logger.class.getClassLoader().getResource("/log4j.properties");
        if (resource == null) {
            resource = Logger.class.getClassLoader().getResource("log4j.properties");
        }

        PropertyConfigurator.configure(resource);
    }
}
