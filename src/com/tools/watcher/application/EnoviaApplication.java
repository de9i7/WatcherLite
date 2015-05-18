package com.tools.watcher.application;

import org.apache.log4j.Logger;
import com.tools.watcher.application.gui.GuiBuilder;
import com.tools.watcher.framework.ApplicationContext;
import com.tools.watcher.framework.exceptions.InitializationException;

/**
 *
 */
public class EnoviaApplication {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(EnoviaApplication.class);

    private GuiBuilder m_view;

    /**
     *
     */
    public EnoviaApplication() {
    }

    public static void launch() throws InitializationException {
        ApplicationContext ctx = ApplicationContext.build();
        GuiBuilder.build(ctx);
    }
}
