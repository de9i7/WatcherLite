package com.tools.watcher.framework.action;

import com.tools.watcher.controller.Application;
import com.tools.watcher.framework.action.annotation.Action;
import com.tools.watcher.framework.action.annotation.ActionHandler;
import com.tools.watcher.framework.exceptions.ClassValidationException;
import org.apache.log4j.Logger;

/**
 *
 */
@ActionHandler
public class LaunchSettingsHandler extends AbstractAction {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(LaunchSettingsHandler.class);

    private Application m_context;

    /**
     * Initializes application context
     * @param context
     */
    public LaunchSettingsHandler(Application context) {
        this.m_context = context;
    }

    @Action
    public void setSetting() {
        System.out.println("Set setting");
    }

    @Action
    public void displaySomething() {
        System.out.println("Display");
    }

    @Override
    public LaunchSettingsHandler build(
        Application ctx
    ) throws ClassValidationException
    {
        LaunchSettingsHandler result = new LaunchSettingsHandler(ctx);
        result.invalidate();
        // TODO:
        return result;
    }
}
