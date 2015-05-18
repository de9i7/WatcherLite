package com.sandbox.multithreading.pingpong;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DKachurovskiy on 8/20/2014.
 */
public class EntryPoint {

    private String name;

    public static void main(String[] args) {
        try {
            new EntryPoint().run();
        } catch (IllegalArgumentException e) {
            System.out.println("ILLEGAL: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryPoint that = (EntryPoint) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    private void run() {

        final PingPong pp = new PingPong();

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(pp.ping());
                    try {
                        System.out.println(Thread.currentThread().getName() + "IS WAITING...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable producer2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(pp.ping());
                    try {
                        System.out.println(Thread.currentThread().getName() + "IS WAITING...");
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(pp.pong());
                    try {
                        System.out.println("PONG IS WAITING...");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.execute(producer);
//        executor.execute(producer2);

        try {
            System.out.println("---------Start delay-------------");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread consumerThread = new Thread(consumer);
//        consumerThread.start();

        List<String> ss = new ArrayList<String>();
        ss.iterator();
        ss.add("Some string");
        ss.add("Some string");
        ss.add("Some string2");

        Set<String> set = new TreeSet<String>(ss);
        for (String item : set) {
            System.out.println("Item: " + item);
        }

        ExecutorService es = Executors.newSingleThreadExecutor();
        StoppableTask st = new StoppableTask();
        Thread th = new Thread(st);
        th.start();
//        es.execute(st);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
////        st.tellMeToStop();
//        es.shutdown();
        th.interrupt();

//        try {
//            set.add(null);
//        } catch (NullPointerException e) {
//            System.out.print("EXCEPTION!!!");
//            throw new IllegalArgumentException("From here 1.");
//        } finally {
//           throw new IllegalArgumentException("From here 2.");
//        }


//        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(5);
//        try {
//            bq.take();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



//        producerThread.start();

    }


    public class StoppableTask implements Runnable {
        private  boolean pleaseStop;

        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1500);
                    System.out.println("Stoppable iteration... " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        }

        public void tellMeToStop() {
            pleaseStop = true;
        }
    }


    /**
     *
     */
    private class PingPong {

        private String msg;

        private boolean received = false;

        private synchronized String ping() {
            while (!received) {
                try {
                    System.out.println("Waiting: " + Thread.currentThread().getName());
                    wait();
                    System.out.println(Thread.currentThread().getName() + " - NOTIFIED");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            try {
//                System.out.println("PING IS WAITING...");
//                Thread.sleep(2500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            received = false;
            notifyAll();
//            try {
//                System.out.println(Thread.currentThread().getName() + "delay after notify...");
//                Thread.sleep(3500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return "ping by " + Thread.currentThread().getName();
        }

        private synchronized String pong() {
            while (received) {
                try {
                    System.out.println("--> Waiting PONG: " + Thread.currentThread().getName());
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            try {
//                System.out.println("PONG IS WAITING...");
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            received = true;
            notifyAll();
            return "pong";
        }

    }
}
