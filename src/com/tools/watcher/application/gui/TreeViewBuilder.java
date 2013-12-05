package com.tools.watcher.application.gui;

import com.tools.watcher.application.controller.FilesystemActionsHandler;
import com.tools.watcher.application.controller.model.filesystem.Directory;
import com.tools.watcher.framework.ApplicationContext;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.io.File;

/**
 *
 */
public class TreeViewBuilder {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(TreeViewBuilder.class);

    /** */
    private final Class<?> m_handler;

    /** Main tree view instance*/
    private JTree m_tree;

    private ApplicationContext m_context;
    /**
     * Private constructor
     * @param ctx
     */
    private TreeViewBuilder(ApplicationContext ctx, Class<?> handlerClass){
        m_context = ctx;
        m_handler = handlerClass;
    }

    /**
     *
     * @return
     */
    public static JComponent build(ApplicationContext ctx, Class<?> handlerClass) {
        return new TreeViewBuilder(ctx, handlerClass).buildTreeView();
    }

    /**
     *
     * @return
     */
    private JComponent buildTreeView() {
        m_tree= new JTree(prepareTreeViewData());
        //Where the tree is initialized:
        m_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        m_tree.addTreeSelectionListener(new TreeComponentListener());
        return new JScrollPane(m_tree);
    }

    /**
     *
     * @return
     */
    private DefaultMutableTreeNode prepareTreeViewData() {
        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("The Java Series");

        final FilesystemActionsHandler fsHandler = (FilesystemActionsHandler) m_context.getActionService().
                find(m_handler);
        fsHandler.getDirectoryContent(
                new Directory(new File("d:\\prj\\boeing\\etl\\trunk\\SourceCode\\EID_ETL\\ETL_ENOVIA\\conf")),
                top
        );
        return top;
    }

    private class TreeComponentListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) m_tree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }

            if (node.isLeaf()){
                JOptionPane.showMessageDialog(null, "Leaf: " + node.getUserObject());
            } else {
                JOptionPane.showMessageDialog(null, "Root!");
            }
        }
    }
}
