package com.sandbox.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 10/17/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsyncLoad {
    private ScheduledExecutorService m_executor = null;

    protected boolean m_idle = true;
    protected Queue<String> m_itemQueue = new LinkedBlockingDeque<String>();

    private boolean m_shouldStop;

    public static void main(String[] args) {
        new AsyncLoad().run();
    }

    private void run() {
        List<String> itemsToSubmit = new ArrayList<String>();
        itemsToSubmit.add("Task 1");
        itemsToSubmit.add("Task 2");
        itemsToSubmit.add("Task 3");
        itemsToSubmit.add("Task 4");
        itemsToSubmit.add("Task 5");
        itemsToSubmit.add("Task 6");

        System.out.println("Submit items -> ");
        for (String item : itemsToSubmit){
            submit(item);
        }
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }

    private void resubmit(String item) {
        System.out.println("Item : [" + item + "] is going to be resubmitted.");
        submit(item);
    }

    /**
     * Sets special should-stop flag to true.
     */
    public void onShouldStopFlag() {
        m_shouldStop = true;
    }

    /**
     * Returns should-stop flag value.
     * @return true - if flag is on, false - otherwise
     */
    public boolean  shouldStop() {
        return m_shouldStop;
    }

    protected synchronized void setIdle(final boolean idle) {
        m_idle = idle;
    }

    /**
     * Submits item for asynchronous uploading to Tameran
     *
     * @param loadItem loading item
     */
    public synchronized void submit(String loadItem) {
        m_itemQueue.add(loadItem);
        if (m_idle) {
            resume();
        }
    }

    /*

    ----------------------------------Execute service----------------------------------------------

     */
    /**
     *
     * @param process
     * @param delay
     * @param tu
     * @return
     */
    public final synchronized ScheduledFuture submit(final Runnable process, long delay, TimeUnit tu) {

        final ScheduledExecutorService service = getExecutorService();
        return service.schedule(process, delay, tu);
    }

    protected final ScheduledExecutorService getExecutorService() {
        if (m_executor == null) {
            m_executor = new LoggedScheduledThreadPool(5);

        }
        ((ScheduledThreadPoolExecutor) m_executor).setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        ((ScheduledThreadPoolExecutor) m_executor).setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        return m_executor;
    }

    /*

    -------------------------------------------------------------------------------------------------

     */

    protected synchronized void resume() {
        if (!m_idle) {
            // internal queue monitor is running
            return;
        }
        setIdle(false);
        submit(new Runnable() {
            @Override
            public void run() {
                while (m_itemQueue.size() > 0) {
                    if (shouldStop() || Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    load(m_itemQueue.poll());
                }
                setIdle(true);
                if(m_itemQueue.size() > 0) {
                    resume();
                }
            }
        }, 100, TimeUnit.MILLISECONDS);
    }

    /**
     *
     * @param item
     */
    protected void load(String item){
        try {
            long sleepTime = (long) (Math.random()*10000);
            System.out.println("Sleep : " + sleepTime + "Loading : " + item);
            Thread.sleep(sleepTime);
            if (getRandomBoolean()) {
                resubmit(item);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
