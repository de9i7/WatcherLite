package com.sandbox.syncronizing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 */
public class CopyAndWriteTest {

    // ---------------- BEReportWorker imitation --------------------
    private class Deleter implements Runnable {

        private final Reader m_reader;

        public Deleter(Reader reader) {
            m_reader = reader;
        }

        @Override
        public void run() {
            try {
                deleteItems();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void deleteItems() throws InterruptedException {

            // Delete items one by one
            int size = m_reader.getCollectionSize();
            while (size != 0) {
                m_reader.removeItem();
                // With delay!!!
                Thread.sleep(100);
                size--;
            }
        }

    }

    // ---------------- Translation service imitation --------------------
    private class Reader implements Runnable {

        private List<Integer> m_container;

        @Override
        public void run() {
            m_container = prepareContainer();

            new Thread(new Deleter(this)).start();

            try {
                readContainer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void readContainer() throws InterruptedException {
            Thread.sleep(1000);
            System.out.println("Start reading container first time.");

//          We offer to use this solution ->
//          Integer[] array = m_container.toArray(new Integer[m_container.size()]);
//
//          ...and use "array" instance further for both iterators. To check it
//          replace m_container to array instance.

            // First iterator
            for (Integer item : m_container) {
                System.out.println("Read item " + item);
            }

            // Synthetic delay. Prepare SQL string while other workers
            // could delete some ids from container
            Thread.sleep(2000);

            // Second iterator
            System.out.println("Start reading container second time.");
            for (Integer item : m_container) {
                System.out.println("Read item " + item);
            }
        }

        private List<Integer> prepareContainer() {
            List<Integer> result = new CopyOnWriteArrayList<Integer>();
            for (int i = 0; i < 50; i++) {
                result.add(i);
            }
            return result;
        }


        public void removeItem() {
            System.out.println("Item to delete: " + m_container.get(0));
            m_container.remove(0);
        }

        public int getCollectionSize() {
            return m_container.size();
        }
    }

    public static void main(String[] args) {
        new CopyAndWriteTest().run();
    }

    private void run() {
        new Thread(new Reader()).start();
    }
}
