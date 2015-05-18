package com.sandbox.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 10/18/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TypesTest {
    public static int LOAD_PORTION = 60;

    private List<String> queue = new ArrayList<String>();
    private int some;

    public static void main(String[] args) {
        new TypesTest().run();
    }

    private boolean run() {

        some += 5;
        System.out.println(some);

        String testItem = new String("Tst");

        queue = new ArrayList<String>();
        queue.add("Item1");
        queue.add("Item2");
        queue.add(testItem);
        queue.add("Item3");
        queue.add("Item4");

        System.out.println(queue);

        queue.remove(testItem);

        System.out.println(queue);

        queue.remove(testItem);

        System.out.println(queue);

        return queue.size() > LOAD_PORTION * 0.1f;

    }
}
