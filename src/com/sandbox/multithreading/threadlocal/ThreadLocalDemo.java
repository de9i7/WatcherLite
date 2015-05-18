package com.sandbox.multithreading.threadlocal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by DKachurovskiy on 8/26/2014.
 */
public class ThreadLocalDemo {

    public static class SomeBuilder {
        private int counter;

        public void build() {
            System.out.println("Thread " + Thread.currentThread().getName() + " Build some structure...");

            counter++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ioe) {
                ioe.printStackTrace();
            }
        }

        public int getCounter() {
            return counter;
        }
    }

    public static class SomeBuilderThread implements Runnable {

        private SomeBuilder sb;

        public SomeBuilderThread(SomeBuilder sb) {
            Deque<Object> dq = new ArrayDeque<Object>();
            Queue<Object> qu = new PriorityQueue<Object>();

            Map<String, Object> tree = new TreeMap<String, Object>( );
            qu.poll();
            this.sb = sb;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                sb.build();
            }
            System.out.println("My name is " + Thread.currentThread().getName() + " and I built " + sb.getCounter() + " things.");
        }
    }

    public static void main(String[] args) {
        SomeBuilder sb = new SomeBuilder();

        Thread thread1 = new Thread(new SomeBuilderThread(sb));
        Thread thread2 = new Thread(new SomeBuilderThread(sb));

        thread1.start();
        thread2.start();
    }
}
