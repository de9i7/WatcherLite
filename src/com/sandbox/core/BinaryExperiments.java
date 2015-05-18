package com.sandbox.core;

/**
 * Created by DKachurovskiy on 8/28/2014.
 */
public class BinaryExperiments {

    public static void main(String[] args) {
        new BinaryExperiments().run();
    }

    private void run() {
        int integerVar = -2;
        int negativeIntegerVar = -5;

        Integer reversed = Integer.reverse(integerVar);
        String stringedInteger = Integer.toBinaryString(integerVar);
        String stringedIntegerReversed = Integer.toBinaryString(reversed);
        System.out.println("BITS: " + stringedInteger);
        System.out.println("REVS: " + stringedIntegerReversed);
    }

}
