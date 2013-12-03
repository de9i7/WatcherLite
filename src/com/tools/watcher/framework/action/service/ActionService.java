package com.tools.watcher.framework.action.service;

import com.tools.watcher.framework.action.AbstractAction;
import com.tools.watcher.framework.configuration.Configuration;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 *
 */
public class ActionService {

    /** Log4j Logger */
    private static final Logger LOGGER = Logger.getLogger(ActionService.class);
    /**
     *
     */
    private Configuration m_config;

    private Map<String, AbstractAction> m_actions;

    /**
     *
     * @param config
     */
    public ActionService(Configuration config) {
        m_config = config;
    }

    /**
     *
     */
    public void init() {

        String targetPackage = m_config.getProperty(Configuration.PROPERTY_ACTIONS_PACKAGE);

        m_actions = ActionFinder.loadActions(targetPackage);

        if (m_actions.isEmpty()) {
            LOGGER.warn("No actions were found!");
        }

    }



    public static void invoke() {

    }
}
