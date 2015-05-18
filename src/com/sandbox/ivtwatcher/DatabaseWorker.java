package com.sandbox.ivtwatcher;

import java.util.ArrayList;
import java.util.List;

import com.sandbox.ivtwatcher.gui.ProcessesModel;
import com.sandbox.ivtwatcher.model.IvtProcess;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseWorker implements Runnable {

    private final MainPage m_mainPage;

    public DatabaseWorker(MainPage m_mainPage) {
        this.m_mainPage = m_mainPage;
    }

    @Override
    public void run() {
        System.out.println("Table has been updated");

        List<IvtProcess> processes = DataMonitorDao.getActiveProcesses();
        List<String> columns = new ArrayList<String>();
        columns.add("ETTSK_TASK_ID");
        columns.add("ETIPR_SERVER_NAME");
        columns.add("ETIPR_PATH");
//      ETIPR_IVT_PROCESS_ID

        m_mainPage.getProcessTable().setModel(new ProcessesModel(columns, processes));
//        m_mainPage.getProcessTable().updateUI();


    }
}
