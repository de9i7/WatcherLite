package com.sandbox.syncronizing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class ArrayConsistency {

    /**
     * Main thread imitation
     */
    private class Reader implements Runnable {

        private List<Integer> collection;

        @Override
        public void run() {
            collection = prepareCollection();

            Thread writerThread1 = new Thread(new Writer(this, "1", 20));
            writerThread1.start();
            Thread writerThread2 = new Thread(new Writer(this, "2", 40));
            writerThread2.start();
            Thread writerThread3 = new Thread(new Writer(this, "3", 60));
            writerThread3.start();

            try {
                prepareString(collection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void prepareString(List<Integer> collection) throws InterruptedException {
            Thread.sleep(1000);
            System.out.println("[READER]::Start preparing string");

            Integer[] cache = collection.toArray(new Integer[collection.size()]);
//            synchronized (collection) {
            for (Integer item : cache) {
                System.out.println("[READER]::Read item: " + item);
                Thread.sleep(50);
            }
//            }
        }

        private List<Integer> prepareCollection() {
            List<Integer> result = Collections.synchronizedList(new ArrayList<Integer>());
            for (int i = 0; i < 100; i++) {
                result.add(i);
            }
            return result;
        }

        public synchronized void removeReport(Integer index) {
            System.out.println("[READER]::Delete item: " + index);
            collection.remove(index);
        }

        public List<Integer> getItemsCollection() {
            return collection;
        }

        public Integer getItemsCollectionSize() {
            return collection.size();
        }
    }

    /**
     * Worker thread imitation
     */
    private class Writer implements Runnable {

        private final Reader m_reader;
        private final Integer m_deleteIndex;
        private final String m_name;

        public Writer(Reader readerThread, String name, Integer deleteIndex) {
            m_reader = readerThread;
            m_deleteIndex = deleteIndex;
            m_name = name;
        }

        @Override
        public void run() {
            try {

                for (int i = m_deleteIndex; i < (m_deleteIndex + 20); i++) {
                    m_reader.removeReport(i);
                    System.out.println("[WRITER " + m_name + "]::Item deleted: " + i);
//                    System.out.println("[WRITER]::Size: " + m_reader.getItemsCollectionSize());
                    Thread.sleep(100);
//                    System.out.println("[WRITER]::Size: " + m_reader.getItemsCollectionSize());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("[WRITER " + m_name + "]::Stop deleting");
        }
    }

    public static void main(String[] args) {
        new ArrayConsistency().run();
    }

    private void run() {
//        List<Integer> coll = prepareCollection();
//        Integer[] array = coll.toArray(new Integer[coll.size()]);
//
//        coll.remove(5);
//        coll.remove(7);
//        for (Integer item : array) {
//            System.out.println("Item: " + item);
//        }
        Thread readerThread = new Thread(new Reader());
        readerThread.start();

    }

    private List<Integer> prepareCollection() {
        List<Integer> result = Collections.synchronizedList(new ArrayList<Integer>());
        for (int i = 0; i < 10; i++) {
            result.add(i);
        }
        return result;
    }

}
