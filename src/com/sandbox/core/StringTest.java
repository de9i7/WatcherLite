package com.sandbox.core;

/**
 * Created by DKachurovskiy on 8/12/2014.
 */
public class StringTest{
    public static void main(String[] args) throws InterruptedException {
        StringTest st = new StringTest();
        st.run();
        System.out.println("Lets continue");
        st.run2();
    }

    private void run() throws InterruptedException {

        System.out.println("Start...");
        Thread.sleep(5000);
        String initial = "";
        for (int i = 0; i < 10000; i++) {
            initial += i;
//            if (i == 2000) {
//                initial.intern();
//            }
            Thread.sleep(3);
        }
         System.out.println("Target string size: " + initial.length());
    }

    private void run2() throws InterruptedException {

        System.out.println("Start...");
        Thread.sleep(2000);
        String initial = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
            Thread.sleep(3);
        }
        System.out.println("Target string size: " + initial.length());
    }
}
