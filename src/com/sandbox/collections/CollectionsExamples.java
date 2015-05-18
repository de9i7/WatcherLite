package com.sandbox.collections;

import com.sandbox.algorithms.quicksort.Quicksort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DKachurovskiy on 9/1/2014.
 */
public class CollectionsExamples {

    public static void main(String[] args) {
        new CollectionsExamples().run();
    }

    public void quickSort(int[] elements) {
        Quicksort qs = new Quicksort();
        qs.sort(elements);
    }

    private void run() {
        String[] valuesToSave = {"value1", "value2", "value3", "final text"};

        // Queue
        Queue<String> priorityQueue = new PriorityQueue<String>();

        BlockingQueue<String> queue = new LinkedBlockingDeque<String>();

        Lock lock = new ReentrantLock();
        lock.lock();

        List<String> cowArray = new CopyOnWriteArrayList<String>();

        // Deque
        Deque<String> arrayDeque = new ArrayDeque<String>();
        System.out.println("---- ArrayDeque initial ----");
        Profiler.beginMeasure();
        for (String txt : valuesToSave) {
            arrayDeque.push(txt);
        }
        System.out.println(Profiler.resultAsString());

        System.out.println();

        // Deque
        List<String> linkedList = new LinkedList<String>();
        linkedList.iterator();
        System.out.println("---- LinkedList initial ----");
        Profiler.beginMeasure();
        for (String txt : valuesToSave) {
            linkedList.add(txt);
        }
        System.out.println("Subsequent: " + Profiler.resultAsString());

        Profiler.beginMeasure();
        linkedList.add(1, "test");
        System.out.println("Arbitrary: " + Profiler.resultAsString());


        // ArrayList
        List<String> arrayList = new ArrayList<String>();
        System.out.println("---- ArrayList initial ----");
        Profiler.beginMeasure();
        for (String txt : valuesToSave) {
            arrayList.add(txt);
        }
        System.out.println("Subsequent: " + Profiler.resultAsString());

        Profiler.beginMeasure();
        arrayList.add(1, "test");
        System.out.println("Arbitrary: " + Profiler.resultAsString());

        Map<String, String> tm = new TreeMap<String, String>();
        tm.put("first", "value");

//        startThreadTest();

        System.out.println("Start queues");
        Queue<String> exampleQueue = new PriorityQueue<String>();
        exampleQueue.offer("Offered");
        if (exampleQueue.peek().equalsIgnoreCase("Offered")) {
            exampleQueue.poll();
        }

        System.out.println("Start linkedqueues");
        Queue<String> exampleQueueList = new LinkedList<String>();
        exampleQueueList.offer("Offered");
        if (exampleQueueList.peek().equalsIgnoreCase("Offered")) {
            exampleQueueList.poll();
        }


    }

}
