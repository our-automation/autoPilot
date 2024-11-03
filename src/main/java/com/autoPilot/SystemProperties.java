package com.autoPilot;

/**
 * @author Maheswara
 * @created on 04/08/24
 */

public interface SystemProperties {
    String USER_DIR = System.getProperty("user.dir") + "/";
    String APPIUM_URL = System.getProperty("APPIUM_URL");
    String DEVICE_NAME = System.getProperty("DEVICE_NAME");
    String BROWSER_NAME = System.getProperty("BROWSER_NAME");
    String PROP_DIR_PATH = System.getProperty("PROP_DIR_PATH");
    String RETRY_COUNT = System.getProperty("RETRY_COUNT");


}
