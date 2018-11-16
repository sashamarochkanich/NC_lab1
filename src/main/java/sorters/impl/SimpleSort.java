package sorters.impl;

import analyzer.Configuration;
import fillers.Filler;
import sorters.Sorter;

import java.util.Arrays;

public class SimpleSort implements Sorter{

    private static SimpleSort instance;
    private final String nameOfSorting = "Arrays.sort()";
    private final int[][] recordsOfTime;

    private SimpleSort(){
        this.recordsOfTime = new int[Configuration.NUMBER_OF_ARRAY_SIZE][Configuration.NUMBER_OF_FILLERS];
    }

    public static SimpleSort getInstance(){
        if (instance == null){
            instance = new SimpleSort();
        }
        return instance;
    }

    @Override
    public void sortArray(int[] array) {
        Arrays.sort(array);
    }

    @Override
    public String toString() {
        return nameOfSorting;
    }

    @Override
    public int getRecord(Integer arraySize, Filler filler) {
        return recordsOfTime[Configuration.getIndexOfArraySize(arraySize)][Configuration.getIndexOfFiller(filler)];
    }

    @Override
    public void addRecord(Integer arraySize, Filler filler, int value) {
        this.recordsOfTime[Configuration.getIndexOfArraySize(arraySize)][Configuration.getIndexOfFiller(filler)] = value;
    }
}
