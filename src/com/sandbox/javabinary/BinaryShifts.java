package com.sandbox.javabinary;

import com.sandbox.xsd_checksum_updater.Checksum;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 11/19/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryShifts {

    public static void main(String[] args) {
        new BinaryShifts().run();
    }

    private void run() {
        Integer mask = 0xFFFF;
        int val = 0x2222;

        System.out.println("Value: " + Integer.toBinaryString(val & mask));
        System.out.println("Mask: " + Integer.toBinaryString(mask));

        System.out.println("Checksum: " + Checksum.getChecksumMD5("Some text".getBytes()));

    }
}

