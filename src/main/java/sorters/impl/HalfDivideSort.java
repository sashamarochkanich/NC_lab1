package sorters.impl;

import analyzer.Configuration;
import fillers.Filler;
import sorters.Divider;
import sorters.Merger;
import sorters.Sorter;

import java.util.Arrays;
import java.util.HashMap;

public class HalfDivideSort implements Sorter, Merger, Divider {

    private static final HashMap<Sorter, HalfDivideSort> instance = new HashMap<>();
    private final String nameOfSorting = " with Half-divide method";
    private final int[][] recordsOfTime;
    private final Sorter sortMethod;

    private HalfDivideSort(Sorter sorter) {
        this.recordsOfTime = new int[Configuration.NUMBER_OF_ARRAY_SIZE][Configuration.NUMBER_OF_FILLERS];
        this.sortMethod = sorter;
    }

    public static HalfDivideSort getInstance(Sorter sorter){
        if (instance.get(sorter) == null){
            instance.put(sorter, new HalfDivideSort(sorter));
        }
        return instance.get(sorter);
    }

    @Override
    public int divideArray(int[] array, int firstIndexOfElement, int lastIndexOfElement) {
        return (firstIndexOfElement + lastIndexOfElement)/2;
    }

    @Override
    public void merge(int[] array, int firstIndexOfFirstSubArray, int lastIndexOfFirstSubArray, int lastIndexOfSecondSubArray) {

        int[] firstSubArray = Arrays.copyOfRange(array,firstIndexOfFirstSubArray, lastIndexOfFirstSubArray + 1);
        int[] secondSubArray = Arrays.copyOfRange(array,lastIndexOfFirstSubArray + 1, lastIndexOfSecondSubArray + 1);

        sortMethod.sortArray(firstSubArray);
        sortMethod.sortArray(secondSubArray);

        int k = 0;
        int i = 0;
        int j = 0;

        while (i < firstSubArray.length && j < secondSubArray.length){
            if (firstSubArray[i] <= secondSubArray[j]){
                array[k] = firstSubArray[i];
                i++;
            } else {
                array[k] = secondSubArray[j];
                j++;
            }
            k++;
        }

        while (i < firstSubArray.length){
            array[k] = firstSubArray[i];
            i++;
            k++;
        }

        while (j < secondSubArray.length){
            array[k] = secondSubArray[j];
            j++;
            k++;
        }
    }

    @Override
    public void sortArray(int[] array) {
        int indexOfDividingElement = divideArray(array, 0, array.length - 1);
        merge(array,0, indexOfDividingElement,array.length - 1);
    }

    public Sorter getSortMethod() {
        return sortMethod;
    }

    @Override
    public String toString() {
        return sortMethod.toString() + nameOfSorting;
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
