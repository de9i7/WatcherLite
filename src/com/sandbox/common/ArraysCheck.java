package com.sandbox.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 11/26/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArraysCheck {

    public static void main(String[] args) throws InterruptedException {
        new ArraysCheck().run();
    }

    private void run() throws InterruptedException {

        BlockingQueue<String> linkedBQueue = new LinkedBlockingQueue<String>(2);
        linkedBQueue.put("Java"); //puts object into BlockingQueue
        System.out.println("size of BlockingQueue before peek : " + linkedBQueue.size());
        System.out.println("example of peek() in BlockingQueue: " + linkedBQueue.peek());
        System.out.println("size of BlockingQueue after peek : " + linkedBQueue.size());
        System.out.println("calling poll() on BlockingQueue: " + linkedBQueue.poll());
        System.out.println("size of BlockingQueue after poll : " + linkedBQueue.size());

        long testLong = 0;
        System.out.println(testLong);

    }
}
