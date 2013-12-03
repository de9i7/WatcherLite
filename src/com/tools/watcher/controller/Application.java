package com.tools.watcher.controller;

import com.tools.watcher.framework.ApplicationContext;
import com.sandbox.xsd_checksum_updater.config.Configuration;

/**
 *
 */
public abstract class Application {

    /**
     * Performs configuration-specific processing and prepare
     * corresponding instance.
     * @return Application implementation.
     */
    protected Application build(Configuration configuration) {
        // TODO: Configuration-dependent processing.
        ApplicationContext ctx = ApplicationContext.build();
        return new EnoviaApplication().build(configuration);
    }
}
