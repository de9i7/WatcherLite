package com.tools.watcher.gui;

import com.tools.watcher.controller.model.filesystem.IDirectoryItem;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.util.Map;

/**
 *
 */
public class PropertiesView {

    /** Logger instance */
    private static final Logger LOG = Logger.getLogger(PropertiesView.class);

    /** Main tree view instance*/
    private JTree m_tree;

    /** Properties View instance */
    private static PropertiesView instance = new PropertiesView();

    /**
     * Private constructor
     */
    private PropertiesView(){
    }

    /**
     *
     * @return
     */
    public static PropertiesView getInstance() {
        return instance;
    }

    /**
     *
     * @return
     */
    public JComponent build() {
        LOG.info("Controller building : ");

        DefaultMutableTreeNode top =
                new DefaultMutableTreeNode("The Java Series");
        prepareSettings(top);

        m_tree = new JTree(top);
        //Where the tree is initialized:
        m_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //Listen for when the selection changes.
        m_tree.addTreeSelectionListener(new TreeComponentListener());

        JScrollPane treeView = new JScrollPane(m_tree);

        return treeView;
    }

    /**
     *
     * @param top
     */
    private void prepareSettings(DefaultMutableTreeNode top) {
//        LOG.info("Controller :" + m_controller);
//        final IDirectoryItem dir = m_controller.getDirectoryContent();
//        readDirectoryContent(dir, top);
    }

    /**
     * Recursively reads directory content.
     * @param dir
     * @param top
     */
    private void readDirectoryContent(
            IDirectoryItem dir,
            DefaultMutableTreeNode top
    ) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
        if (dir.getItemType() == IDirectoryItem.FOLDER) {
            Map<String, IDirectoryItem> items = dir.read();
            for (Map.Entry<String, IDirectoryItem> entry : items.entrySet()) {
                if (dir.getItemType() == IDirectoryItem.FOLDER) {
                    category = new DefaultMutableTreeNode(entry.getValue().getName());
                    top.add(category);
                    readDirectoryContent(entry.getValue(), category);

                } else if (dir.getItemType() == IDirectoryItem.PROPERTY) {
                    book = new DefaultMutableTreeNode(dir.getName());
                    category.add(book);
                }
            }
        }

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
