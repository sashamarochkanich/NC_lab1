package analyzer;

import fillers.Filler;
import sorters.Sorter;


import java.util.ArrayList;
import java.util.TreeSet;

public class Configuration {

    // TODO: 11.11.2018  Delete global variables
    public static final int NUMBER_OF_ARRAY_SIZE = 50;
    public static final int NUMBER_OF_FILLERS = 4;

    private static final ArrayList<Sorter> listOfSorters = new ArrayList<>();
    private static final ArrayList<Filler> listOfFillers = new ArrayList<>();
    private static final TreeSet<Integer> listOfArraysSize = new TreeSet<>();
    private static int defaultOrigin;
    private static int defaultBoundForRandom;

    public static ArrayList<Sorter> getListOfSorters() {
        return listOfSorters;
    }

    public static ArrayList<Filler> getListOfFillers() {
        return listOfFillers;
    }

    public static TreeSet<Integer> getListOfArraysSize() {
        return listOfArraysSize;
    }

    public static int getIndexOfFiller(Filler filler) {
        return listOfFillers.indexOf(filler);
    }

    public static int getIndexOfArraySize(Integer arraySize) {
        return listOfArraysSize.headSet(arraySize).size();
    }

    public static void addSorter(Sorter sorter) {
        Configuration.listOfSorters.add(sorter);
    }

    public static void removeSorter(Sorter sorters) {
        Configuration.listOfSorters.remove(sorters);
    }

    public static void addFiller(Filler filler) {
        Configuration.listOfFillers.add(filler);
    }

    public static void removeFiller(Filler filler) {
        Configuration.listOfFillers.remove(filler);
    }

    public static void addArraySize(Integer sizeOfArray) {
        Configuration.listOfArraysSize.add(sizeOfArray);
    }

    public static void removeArraySize(Integer sizeOfArray) {
        Configuration.listOfArraysSize.remove(sizeOfArray);
    }

    public static int getDefaultBoundForRandom() {
        return defaultBoundForRandom;
    }

    public static void setDefaultBoundForRandom(int defaultBoundForRandom) {
        Configuration.defaultBoundForRandom = defaultBoundForRandom;
    }

    public static int getDefaultOrigin() {
        return defaultOrigin;
    }

    public static void setDefaultOrigin(int defaultOrigin) {
        Configuration.defaultOrigin = defaultOrigin;
    }

}
