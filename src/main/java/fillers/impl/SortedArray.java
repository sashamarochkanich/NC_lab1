package fillers.impl;

import fillers.Filler;

public class SortedArray implements Filler {

    private final String nameOfFiller = "Sorted array";
    private static SortedArray instance;

    public static SortedArray getInstance(){
        if (instance == null){
            instance = new SortedArray();
        }
        return instance;
    }

    @Override
    public String toString() {
        return nameOfFiller;
    }

    @Override
    public int[] generateArray(int arraySize) {
        return generateRandomValues(arraySize).sorted().toArray();
    }
}
