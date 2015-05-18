package com.sandbox.common.object_size;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by DKachurovskiy on 9/18/2014.
 */
public class TestObjectSize {

    private static volatile Instrumentation instrument;

    public static void premain(String agentArgs, Instrumentation inst){
        instrument = inst;
    }

    public static void main(String[] args) {
        new TestObjectSize().run();
    }

    private void run() {

//        iteratateThroughArray();

        Long size = 543l;
        AtomicInteger ai = new AtomicInteger();

        List<String> collection = new LinkedList<String>();

        collection.add("Tst");
        collection.add("Tst2");
        collection.add("stasd");
        collection.add("qwer");
        collection.add("qwer");

        for (Integer i = 0; i < 100000; i++){
            collection.add(i.toString());
        }

        System.out.println("Linked size: " + instrument.getObjectSize(size));
    }

    private void iteratateThroughArray() {
        List<String> items = new ArrayList<String>();
        items.add("test str 1");
        items.add("test str 2");
        items.add("test str 3");


        Iterator<String> iterator = items.iterator();
        String item = null;
        while ((item = iterator.next()) != null) {
            System.out.println("Item: " + item);
        }
    }
}
