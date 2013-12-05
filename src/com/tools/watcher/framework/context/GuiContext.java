package com.tools.watcher.framework.context;

import com.tools.watcher.framework.ApplicationContext;

import javax.swing.*;
import java.util.Map;

/**
 *
 */
public class GuiContext {

    private Map<String, JComponent> m_content;

    private ApplicationContext m_context;

    public static GuiContext build(ApplicationContext ctx) {
        GuiContext guiContext = new GuiContext();
        guiContext.setApplicationContext(ctx);
        return guiContext;
    }

    private void setApplicationContext(ApplicationContext applicationContext) {
        this.m_context = applicationContext;
    }

    public void register(JComponent top) {
        m_content.put(top.getName(), top);
    }
}
