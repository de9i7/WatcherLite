package com.sandbox.common.hierarchy;

/**
 * Created by DKachurovskiy on 9/15/2014.
 */
public class ChildLeft implements Parent {

    public static void main(String[] args) {
        new ChildLeft().run();
    }

    private void run() {
        someMethod("Param");
    }

    @Override
    public void someMethod(String param) {
        String val = "24";
        Integer valInt = new Integer(val);
        System.out.println(valInt);
        System.out.println("Some actions...");
    }
}
