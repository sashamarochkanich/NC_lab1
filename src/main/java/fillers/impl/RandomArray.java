package fillers.impl;

import fillers.Filler;

public class RandomArray implements Filler {

    private final String nameOfFiller = "Random array";
    private static RandomArray instance;

    public static RandomArray getInstance(){
        if (instance == null){
            instance = new RandomArray();
        }
        return instance;
    }

    @Override
    public String toString() {
        return nameOfFiller;
    }

    @Override
    public int[] generateArray(int arraySize) {
        return generateRandomValues(arraySize).toArray();
    }
}
