package com.sandbox.algorithms.mergesort;

import java.util.Arrays;

/**
 * Created by DKachurovskiy on 9/18/2014.
 */
public class Mergesort {
    private int[] numbers;
    private int[] helper;

    private int number;

    public static void main(String[] args) {
        new Mergesort().run();
    }

    private void run() {
        int[] values = {4, 5, 6, 3, 8, 10, 16, 2};
        int[] result;

        System.out.println("Before...");
        System.out.println(Arrays.toString(values));

        sort(values);

        System.out.println("After...");
        System.out.println(Arrays.toString(values));
    }

    public void sort(int[] values) {
        this.numbers = values;
//        System.out.println("numbers - " + this.numbers + "  |  values - " + values);
        number = values.length;
        this.helper = new int[number];
        mergesort(0, number - 1);
    }

    private void mergesort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}
