package com.tools.watcher.framework.action.api;

import com.tools.watcher.framework.ApplicationContext;
import com.tools.watcher.framework.action.ActionFinder;
import com.tools.watcher.framework.configuration.Configuration;
import com.tools.watcher.framework.logger.LoggerService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 *
 */
public class ActionService {

    /** */
    private ApplicationContext m_context;

    /** */
    private LoggerService m_logger;

    /** */
    private Map<Class<?>, Object> m_actions;

    /**
     *
     */
    public static ActionService build(ApplicationContext context) {
        ActionService actionService = new ActionService();

        actionService.setApplicationContext(context);
        actionService.setLogger(context.getLoggerService());

        actionService.initActionsList();

        return actionService;
    }

    private ApplicationContext getApplicationContext() {
        return m_context;
    }

    private void setApplicationContext(ApplicationContext context) {
        this.m_context = context;
    }

    private LoggerService getLogger() {
        return m_logger;
    }

    private void setLogger(LoggerService logger) {
        this.m_logger = logger;
    }

    /**
     *
     */
    public void initActionsList() {
        String targetPackage = m_context.getConfiguration().getProperty(
                Configuration.PROPERTY_ACTIONS_PACKAGE);
        m_actions = ActionFinder.loadActions(targetPackage);

        if (m_actions.isEmpty()) {
            m_logger.logWarning("No actions were found!");
        }
    }

    /**
     * Finds corresponding handler
     * @param clazz
     * @return
     */
    public Object find(Class<?> clazz) {
        Object result = null;
        if (m_actions.containsKey(clazz)) {
            result = m_actions.get(clazz);
            if (result == null) {
                try {
                    Constructor<?> c = clazz.getDeclaredConstructor(ApplicationContext.class);
                    c.setAccessible(true);
                    result = c.newInstance(new Object[] {m_context});
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
