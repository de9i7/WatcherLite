package com.sandbox.common;

import java.util.UUID;

/**
 *
 */
public class UUIDTester {

    public static void main(String[] args) {
        new UUIDTester().run();
    }

    private void run() {
        System.out.println("UUI");
        UUID uid = UUID.randomUUID();
        System.out.println("Random: Least -> " + uid.getLeastSignificantBits() + "  | Most -> " + uid.getMostSignificantBits());

        UUID uid2 = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        System.out.println("From string: Least -> " + uid2.node());
    }
}
