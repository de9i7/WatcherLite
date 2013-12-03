package com.tools.watcher.framework;

import com.tools.watcher.framework.action.service.ActionService;
import com.tools.watcher.framework.configuration.Configuration;
import com.tools.watcher.framework.exceptions.InvalidPropertyException;

/**
 * This class contains primary framework services instances.
 * Also it performs necessary initialization actions.
  */
public class ApplicationContext {

    /**
     * Configuration container
     */
    private static Configuration m_config;

    /**
     * Service that provides UI by actions
     */
    private static ActionService m_actionService;

    /**
     * Controller that processes actions occurred in UI
     */
    private GuiController m_guiController;

    /**
     *
     * @return
     */
    public static ApplicationContext build() {
        ApplicationContext ctx = null;
        try {
            m_config = Configuration.getInstance();


            m_actionService = new ActionService(m_config);
            m_actionService.init();



        } catch (InvalidPropertyException e) {
            e.printStackTrace();
        }

        return ;
    }
}
