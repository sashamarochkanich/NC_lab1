package sorters.impl;

import analyzer.Configuration;
import fillers.Filler;
import sorters.Divider;
import sorters.Sorter;


public class QuickSort implements Sorter, Divider {

    private static QuickSort instance;
    private final String nameOfSorting = "Quick sort";
    private final int[][] recordsOfTime;

    private QuickSort(){
        this.recordsOfTime = new int[Configuration.NUMBER_OF_ARRAY_SIZE][Configuration.NUMBER_OF_FILLERS];
    }

    public static QuickSort getInstance(){
        if (instance == null){
            instance = new QuickSort();
        }
        return instance;
    }

    @Override
    public void sortArray(int[] array) {
        sort(array,0,array.length-1);
    }

    @Override
    public int divideArray(int[] array, int firstIndexOfElement, int lastIndexOfElement){
//        Random random = new Random();
        int pivot = array[lastIndexOfElement];
//        int pivot = array[random.nextInt(lastIndexOfElement)];
        int i = firstIndexOfElement - 1;
        for (int j = firstIndexOfElement; j < lastIndexOfElement; j++){
            if (array[j] <= pivot){
                i++;
                Sorter.swapValues(i, j, array);
            }
        }
        Sorter.swapValues(i + 1, lastIndexOfElement, array);
//        System.out.println(Arrays.toString(array));
        return i + 1;
    }

    private void sort(int array[], int firstIndexOfElement, int lastIndexOfElement){
        if (firstIndexOfElement < lastIndexOfElement){
            int splitIndex = divideArray(array, firstIndexOfElement, lastIndexOfElement);
            sort(array, firstIndexOfElement, splitIndex - 1);
            sort(array, splitIndex + 1, lastIndexOfElement);
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
