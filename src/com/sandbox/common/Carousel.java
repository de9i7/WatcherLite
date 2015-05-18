package com.sandbox.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DKachurovskiy on 10/10/2014.
 */
public class Carousel {

    private final List<String> storagesList = new ArrayList<String>();
    private int lastStorageIndex = 3;
    private String storage;

    public static void main(String[] args) {
        new Carousel().run();
    }

    private void run() {

        storagesList.add("toolong");
        storagesList.add("some1222");
        storagesList.add("some2");
        storagesList.add("toolong");
        storagesList.add("toolong");
        storagesList.add("toolong");

        String storage = findNextUsableStorage(6);
        System.out.println("Sorage: " + storage);
    }


    /**
     * Searches an available storage in the storage list. The searching
     * mechanism consists of two steps. On the first step method searches
     * an available storage in the end of the list. On the second one it
     * looks through storages from the head of the list. Such approach allows
     * system to define storage that has become available during
     * application execution.
     * @param size required file size to store
     * @return usable storage
     */
    private String findNextUsableStorage(final long size) {
        String storage = null;
        final int nextStorageIndex = lastStorageIndex + 1;

        if ((storage = searchStorageInRange(nextStorageIndex, storagesList.size(), size)) == null) {
            storage = searchStorageInRange(0, nextStorageIndex, size);
        }
        return storage;
    }

    /**
     * Iterates through the list of storage and an find available one.
     * @param startIndex left bound of the range
     * @param endIndex right bound of the range
     * @param size required file size to store
     * @return
     */
    private String searchStorageInRange(
            final int startIndex,
            final int endIndex,
            final long size
    ) {
        System.out.println("< search >");
        String storage = null;
        for (int i = startIndex; i < endIndex; i++) {
            String disk = storagesList.get(i);
            if (isDiskUsable(disk, size)) {
                lastStorageIndex = i;
                storage = disk;
                break;
            }
        }
        return storage;
    }

    private boolean isDiskUsable(String disk, long size) {
        return disk.length() <= size;
    }
}
