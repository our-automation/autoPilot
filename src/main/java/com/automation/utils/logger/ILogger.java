package com.automation.utils.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public interface ILogger {
    Logger log = LogManager.getLogger(ILogger.class);
}
