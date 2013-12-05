package com.tools.watcher.framework.logger;

import org.apache.log4j.Logger;

/**
 *
 */
public class LoggerService {

    private static final Logger LOG = Logger.getLogger(LoggerService.class);

    public void logError(String message) {
        LOG.error(message);
    }

    public void logWarning(String message) {
        LOG.warn(message);
    }

    public void logInfo(String message) {
        LOG.info(message);
    }

    public void logDebug(String message) {
        LOG.debug(message);
    }
}
