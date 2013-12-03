package com.tools.watcher.framework.action;

import com.tools.watcher.controller.Application;
import com.tools.watcher.framework.exceptions.ClassValidationException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class AbstractAction {

    protected Map<String, AbstractAction> m_actions = new HashMap<String, AbstractAction>();

    public abstract LaunchSettingsHandler build(Application ctx) throws ClassValidationException;

    /**
     * Invalidates class configuration validity
     */
    public void invalidate() {

    }

    /**
     * Searches method to invoke
     */
    public void invoke(String action) {
        if (m_actions.containsKey(action)) {
            m_actions.get(action);
        } else {
            String packageName = AbstractAction.class.getPackage().getName();

        }
        // TODO: Code to search method to invoke
    }
}
