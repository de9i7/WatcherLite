package com.tools.watcher.application.controller;

import com.tools.watcher.application.controller.model.filesystem.IDirectoryItem;
import com.tools.watcher.framework.ApplicationContext;
import com.tools.watcher.framework.action.annotation.ActionHandler;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;

/**
 *
 */
@ActionHandler
public class FilesystemActionsHandler  {

    /** */
    private ApplicationContext m_context;

    /**
     * Initializes application context intance
     * @param context
     */
    public FilesystemActionsHandler(ApplicationContext context) {
        m_context = context;
    }

    /**
     *
     * @param top
     */
    public void getDirectoryContent(IDirectoryItem dir, DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        if (dir.getItemType() == IDirectoryItem.FOLDER) {
            Map<String, IDirectoryItem> items = dir.read();
            for (Map.Entry<String, IDirectoryItem> entry : items.entrySet()) {
                if (dir.getItemType() == IDirectoryItem.FOLDER) {
                    category = new DefaultMutableTreeNode(entry.getValue().getName());
                    top.add(category);
                    getDirectoryContent(entry.getValue(), category);

                } else if (dir.getItemType() == IDirectoryItem.PROPERTY) {
                    book = new DefaultMutableTreeNode(dir.getName());
                    category.add(book);
                }
            }
        }

    }
}
