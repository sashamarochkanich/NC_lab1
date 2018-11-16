package fillers.impl;

import fillers.Filler;

import java.util.stream.IntStream;

public class SortedArrayWithRandomLastElement implements Filler {

    private final String nameOfFiller = "Sorted array with random last element";
    private static SortedArrayWithRandomLastElement instance;

    public static SortedArrayWithRandomLastElement getInstance() {
        if (instance == null) {
            instance = new SortedArrayWithRandomLastElement();
        }
        return instance;
    }

    @Override
    public String toString() {
        return nameOfFiller;
    }

    @Override
    public int[] generateArray(int arraySize) {
        return IntStream.concat(generateRandomValues(arraySize - 1).sorted(),generateRandomValues(1)).toArray();
    }
}
