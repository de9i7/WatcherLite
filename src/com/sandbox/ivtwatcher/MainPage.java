package com.sandbox.ivtwatcher;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainPage {
    private JFrame m_frame;

    private JPanel panel1;
    private JList list1;
    private JList list2;
    private JList list3;
    private JTabbedPane tabbedPane1;
    private JTable table1;

    /**
     *
     */
    public MainPage() {
        m_frame = new JFrame("MainPage");
        m_frame.setContentPane(panel1);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.pack();
        m_frame.setVisible(true);
    }

    /**
     *
     */
    public static MainPage build(){
        return new MainPage();
    }

    public JTable getProcessTable() {
        return table1;
    }

    public JList getListInput() {
        return list1;
    }

    public JList getListWork() {
        return list2;
    }

    public JList getListOutput() {
        return list3;
    }
}
