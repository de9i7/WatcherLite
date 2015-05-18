package com.sandbox.common.access;

import java.io.IOException;

/**
 * Created by DKachurovskiy on 9/18/2014.
 */
public class OverriderA extends Overriding {

    private static Overriding instance = new OverriderA("Inner class");

    public OverriderA(String someValue) {
        this.someValue = someValue;
    }

    public static Overriding getInstance() {
        return instance;
    }

    @Override
    protected Overriding someValue(Overriding param) throws IOException {

        System.out.println("Tst A inside : " + someValue);
        throw new IOException();
    }
}
