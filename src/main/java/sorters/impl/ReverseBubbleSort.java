package sorters.impl;

import analyzer.Configuration;
import fillers.Filler;
import sorters.Sorter;

public class ReverseBubbleSort implements Sorter {

    private static ReverseBubbleSort instance;
    private final String nameOfSorting = "Reverse bubble sort";
    private final int[][] recordsOfTime;

    private ReverseBubbleSort(){
        this.recordsOfTime = new int[Configuration.NUMBER_OF_ARRAY_SIZE][Configuration.NUMBER_OF_FILLERS];
    }

    public static ReverseBubbleSort getInstance(){
        if (instance == null){
            instance = new ReverseBubbleSort();
        }
        return instance;
    }

    @Override
    public void sortArray(int[] array) {
        boolean swapped;
        for (int i = array.length - 1; i > 0; i--){
            swapped = false;
            for (int j = array.length - 1; j > array.length - 1 - i; j--) {
                if (array[j] < array[j - 1]) {
                    Sorter.swapValues(j, j-1, array);
                    swapped = true;
                }
            }
            if (!swapped){
                break;
            }
        }
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
