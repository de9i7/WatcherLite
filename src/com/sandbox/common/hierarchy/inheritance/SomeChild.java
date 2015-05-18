package com.sandbox.common.hierarchy.inheritance;

/**
 * Created by DKachurovskiy on 9/25/2014.
 */
public class SomeChild extends AbstractParent {

    public static final String SOME_CONST = "tst";

    @Override
    protected Integer someMethod(Number num) {
        return 10;
    }
}
