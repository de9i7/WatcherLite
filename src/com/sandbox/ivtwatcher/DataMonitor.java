package com.sandbox.ivtwatcher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMonitor {

    private final MainPage m_mainPage;

    public DataMonitor(MainPage mainPage) {
        this.m_mainPage = mainPage;
    }

    public void launch() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        //schedule database monitoring thread
        DatabaseWorker databaseWorker = new DatabaseWorker(m_mainPage);
        scheduledThreadPool.scheduleAtFixedRate(databaseWorker, 0, 5, TimeUnit.SECONDS);

        FoldersWorker foldersWorker = new FoldersWorker(m_mainPage);
        scheduledThreadPool.scheduleAtFixedRate(foldersWorker, 0, 5, TimeUnit.SECONDS);

        System.out.println("Threads hs been launched");
    }
}
