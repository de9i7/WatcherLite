package com.tools.watcher.framework;

import com.tools.watcher.framework.action.api.ActionService;
import com.tools.watcher.framework.configuration.Configuration;
import com.tools.watcher.framework.context.GuiContext;
import com.tools.watcher.framework.exceptions.InitializationException;
import com.tools.watcher.framework.exceptions.InvalidPropertyException;
import com.tools.watcher.framework.logger.LoggerService;

import javax.management.remote.rmi.RMIConnection;

/**
 * This class contains primary framework services instances.
 * Also it performs necessary initialization actions.
  */
public class ApplicationContext {

    /**
     * Configuration container
     */
    private Configuration m_config;

    /**
     * Service that provides UI by actions
     */
    private ActionService m_actionService;

    /**
     * Controller that processes actions occurred in UI
     */
    private static GuiContext m_guiContext;

    private LoggerService m_logger;
    private RMIConnection actionService;

    /**
     * @return
     */
    public static ApplicationContext build() throws InitializationException {
        ApplicationContext ctx = new ApplicationContext();
        try {
            ctx.setLoggerService(new LoggerService());

            ctx.setConfiguration(Configuration.getInstance());

            ctx.setActionService(ActionService.build(ctx));

            ctx.setGuiContext(GuiContext.build(ctx));

        } catch (InvalidPropertyException e) {
            ctx.getLoggerService().logError("Error while property loading");
            throw new InitializationException();
        }

        return ctx;
    }

    public GuiContext getGuiContext() {
        return m_guiContext;
    }

    public Configuration getConfiguration() {
        return m_config;
    }

    public LoggerService getLoggerService() {
        return m_logger;
    }

    private void setLoggerService(LoggerService loggerService) {
        this.m_logger = loggerService;
    }


    private void setConfiguration(Configuration configuration) {
        this.m_config = configuration;
    }

    public void setActionService(ActionService actionService) {
        this.m_actionService = actionService;
    }

    public void setGuiContext(GuiContext guiContext) {
        this.m_guiContext = guiContext;
    }

    public ActionService getActionService() {
        return m_actionService;
    }
}
