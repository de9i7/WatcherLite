package com.sandbox.collections;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by DKachurovskiy on 9/19/2014.
 * @author DKachurovskiy
 */
public class SortingTest {

    private CollectionsExamples ce;
    private int[] elements;
    private int[] elementsToSort;

    @Test
    public void testQuickSort() {
        ce = new CollectionsExamples();
        elements = new int[]{ 0, 1, 2, 3, 4, 7, 9 };
        elementsToSort = new int[]{ 9, 3, 2, 1, 4, 7, 0 };
        ce.quickSort(elementsToSort);
        System.out.println(Arrays.toString(elementsToSort));
        assertArrayEquals(elements, elementsToSort);
    }
}
