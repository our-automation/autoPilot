package com.automation.utils.headers;

import com.automation.utils.config.ConfigManager;
import com.automation.utils.exceptions.APIException;
import com.automation.utils.logger.ILogger;
import com.automation.utils.mapper.MapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Maheswara
 * @created on 28/06/21
 */

@Component
public class HeadersUtil implements ILogger {
    private static Map<String, Map<String, String>> headers;

    private static ConfigManager configManager = new ConfigManager();

    static {
        String path = configManager.getString("header.file.path");
        if (path!=null){
            try {
                headers = MapperUtil.getMapper().readValue(new File(path), new TypeReference<Map<String, Map<String, Object>>>() {
                });
            } catch (IOException e) {
                log.error("Failed to convert to Map  " + e);
            }
        }
    }

    public static Map<String, Map<String, String>> getAllHeaders() {
        return headers;
    }
}
