package com.automation.utils.config;

/**
 * @author Maheswara
 * @created on 28/06/21
 */

public enum ConfigProperties {

    ENVIRONMENT("environment");
    private final String key;

    ConfigProperties(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.key;
    }

}