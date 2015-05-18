package com.sandbox.common.access;

import java.io.IOException;

/**
 * Created by DKachurovskiy on 9/18/2014.
 */
public abstract class Overriding {
    protected static Integer SOME_INT = 10;

    protected Integer anotherInt;

    protected String someValue;

    static {
        System.out.println("Static initialized...");
    }

    protected abstract Overriding someValue(Overriding param) throws IOException;
}
