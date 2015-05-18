package com.sandbox.common.access;

/**
 * Created by DKachurovskiy on 9/18/2014.
 */
public class OverriderB extends Overriding {

    private static OverriderB instance = new OverriderB("");

    private OverriderB(String someValue) {
    }

    public static OverriderB getInstance() {
        return instance;
    }

    @Override
    public Overriding someValue(Overriding param) {
        System.out.println("Tst B inside");
        throw new IllegalArgumentException();
    }
}
