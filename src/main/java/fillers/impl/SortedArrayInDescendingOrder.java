package fillers.impl;

import fillers.Filler;

import java.util.Comparator;

public class SortedArrayInDescendingOrder implements Filler {

    private final String nameOfFiller = "Sorted array in Descending order";
    private static SortedArrayInDescendingOrder instance;

    public static SortedArrayInDescendingOrder getInstance() {
        if (instance == null) {
            instance = new SortedArrayInDescendingOrder();
        }
        return instance;
    }

    @Override
    public String toString() {
        return nameOfFiller;
    }

    @Override
    public int[] generateArray(int arraySize) {
        return generateRandomValues(arraySize).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();
    }
}
