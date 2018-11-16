package analyzer;

import fillers.Filler;
import sorters.Sorter;

public class Analyzer {

    public static void analyze(){
        long start, finish;
        for (Integer arraySize: Configuration.getListOfArraysSize()) {
            for (Filler filler : Configuration.getListOfFillers()) {
                int[] array = filler.generateArray(arraySize);
                for (Sorter sorter : Configuration.getListOfSorters()) {
                    int[] tempArray = new int[arraySize];
                    System.arraycopy(array,0,tempArray,0,arraySize);
                    start = System.nanoTime();
                    sorter.sortArray(tempArray);
                    finish = System.nanoTime();
                    sorter.addRecord(arraySize,filler,(int)(finish - start));
                }
            }
        }
    }


}
