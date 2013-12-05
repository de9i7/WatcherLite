package com.tools.watcher.application.gui;

import com.tools.watcher.application.controller.FilesystemActionsHandler;
import com.tools.watcher.framework.ApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 */
public class GuiBuilder extends JFrame {

    private final JComponent m_propertiesView;
    private final ApplicationContext m_context;

    private JLabel m_messageLabel;
    private JButton m_prepareEtlButton;

    public GuiBuilder(String title, ApplicationContext context){
        super(title);

        m_context = context;

        m_propertiesView = TreeViewBuilder.build(m_context, FilesystemActionsHandler.class);
        m_propertiesView.setVisible(true);

        setNativeLookAndFeel();

        JPanel topPanel = prepareHeader();
        JPanel contentPanel = prepareContent();

        Container container = getContentPane();
        container.add(topPanel, BorderLayout.NORTH);
        container.add(contentPanel, BorderLayout.CENTER);

        setSize(1024, 768);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Prepares header panel. Header panel consist of label for
     * messages and common buttons section.
     * @return ready header JPanel instance
     */
    private JPanel prepareHeader() {
        JPanel headerPanel = new JPanel(new GridLayout(2,1));

        JPanel messagePanel = new JPanel(new BorderLayout());

        m_messageLabel = new JLabel("Message");

        messagePanel.setBorder(BorderFactory.createTitledBorder("MESSAGE_TITLE"));
        messagePanel.add(m_messageLabel);

        m_prepareEtlButton = new JButton("PREPARE_ETL_BUTTON");

//        m_prepareEtlButton.setActionCommand();
//        m_prepareEtlButton.addActionListener(this);

        JPanel toolsPanel = new JPanel(new GridLayout(1, 2));
        toolsPanel.setBorder(new EmptyBorder(10, 5, 5, 10));
        toolsPanel.add(m_prepareEtlButton);

        headerPanel.add(messagePanel);
        headerPanel.add(toolsPanel);
        return headerPanel;
    }

    /**
     * Prepares content panel and fills it by
     * necessary components.
     * @return
     */
    private JPanel prepareContent() {
        JPanel contentPanel = new JPanel(new GridLayout(1,2));

        JPanel leftWing = new JPanel(new GridLayout(1,0));
        JPanel rightWing = new JPanel(new GridLayout(1,0));

        leftWing.add(m_propertiesView);

        contentPanel.add(leftWing);
        contentPanel.add(rightWing);
        return contentPanel;
    }

    /**
     *
     */
    private static void setNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
    }

    /**
     * TEST MAIN METHOD
     * @param args
     */
    public static void main(String[] args) {
//        build("Launch", new EnoviaApplication());
    }

    /**
     *
     * @param ctx
     */
    public static void build(ApplicationContext ctx) {
        // TODO: Correct entry point to GuiBuilder
        new GuiBuilder("Get title from property", ctx);
    }
}
