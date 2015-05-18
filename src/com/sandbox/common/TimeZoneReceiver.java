package com.sandbox.common;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by DKachurovskiy on 3/3/2015.
 */
public class TimeZoneReceiver {

    private TimeZone spZone;

    public static void main(String[] args) {
        new TimeZoneReceiver().run();
    }

    private void run() {
        spZone = java.util.TimeZone.getDefault();
        System.out.println(spZone.getID());
        System.out.println(spZone.getOffset(new Date().getTime()));
    }
}
