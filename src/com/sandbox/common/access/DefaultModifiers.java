package com.sandbox.common.access;

import java.io.IOException;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by DKachurovskiy on 9/17/2014.
 */
public class DefaultModifiers {
    static int k;

    static class X {
        int p;

        class Y {
            public void f() {
                System.out.println(k);
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        new DefaultModifiers().run();
        System.out.println("Continue working...");
    }

    private void run() {
        System.out.println("Evrything is ok!");

        Overriding ovv = new OverriderA("From the outside.");

        Overriding ob = OverriderB.getInstance();

        try {
            ob.someValue(ob);
            ovv.someValue(ob);

            Overriding ov = OverriderA.getInstance();

            System.out.println("Now we are going to call method!");
            ov.someValue(ob);


            NavigableSet<String> treeContainer = new TreeSet<String>();
            Set<String> hashContainer = new HashSet<String>();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return;
        }
    }
}
