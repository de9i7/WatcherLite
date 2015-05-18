package com.sandbox.collections;

/**
 * Created by DKachurovskiy on 9/1/2014.
 */
public class Profiler {

    private static long startTime;

    public static void beginMeasure(){
        startTime = System.nanoTime();
    }

    public static String resultAsString() {
        final long result = System.nanoTime() - startTime;
        return " > Time spent: " + result;
    }
}
