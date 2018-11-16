package sorters.impl;

import analyzer.Configuration;
import fillers.Filler;
import sorters.Sorter;

public class BubbleSort implements Sorter {

    private static BubbleSort instance;
    private final String nameOfSorting = "Bubble sort";
    private final int[][] recordsOfTime;

    private BubbleSort() {
        this.recordsOfTime = new int[Configuration.NUMBER_OF_ARRAY_SIZE][Configuration.NUMBER_OF_FILLERS];
    }

    public static BubbleSort getInstance(){
        if (instance == null){
            instance = new BubbleSort();
        }
        return instance;
    }

    @Override
    public void sortArray(int[] array) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++){
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    Sorter.swapValues(j, j+1, array);
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
