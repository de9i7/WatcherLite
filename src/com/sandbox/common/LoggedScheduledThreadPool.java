package com.sandbox.common;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: rmashchenko
 * @date: 4/9/12
 */
public class LoggedScheduledThreadPool extends ScheduledThreadPoolExecutor {

    public LoggedScheduledThreadPool(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(
            r,
            t
        );
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ignored) {

            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // ignore/reset
            }
        }
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        synchronized (this) {
            if(getCorePoolSize() <= getPoolSize() && getPoolSize() < getMaximumPoolSize()) {
                //if core pool size bounds submit we need to increase core pool size
                setCorePoolSize(getPoolSize() + 1);
            }
        }
        return super.schedule(
            command,
            delay,
            unit
        );
    }
}
