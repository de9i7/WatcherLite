package com.sandbox.multithreading.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DKachurovskiy on 9/5/2014.
 */
public class BlockingQueueExample {

    public static final int THREADS = 2;
    private Lock lock = new ReentrantLock();
    private BlockingQueue<String> names;
    private AtomicInteger sharedInt = new AtomicInteger();

    private Object lockObj = new Object();

    public static void main(String[] args) {
        new BlockingQueueExample().run();
    }

    private class LocalTask implements Runnable {


        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println("Thread: " + Thread.currentThread().getName() + "  |  " + sharedInt.incrementAndGet());
                try {
                    names.put("Thread: " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void run() {
        names = new ArrayBlockingQueue<String>(5);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
//        LocalTask tsk = new LocalTask();

        for (int i = 0; i < THREADS; i++) {
            executorService.execute(new LocalTask());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        while (!names.isEmpty()) {
//            try {
//                Thread.sleep(3000);
//                System.out.println("Item from queue: " + names.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        synchronized (lockObj) {
            prepareSomeLock();
        }
    }

    private synchronized void prepareSomeLock() {
        System.out.println("Enter another method...");
        prepareAnotherLock();
        System.out.println("Inside some lock...");
    }

    private synchronized void prepareAnotherLock() {
        System.out.println("Enter second method...");
        System.out.println("Inside another lock...");
    }
}
