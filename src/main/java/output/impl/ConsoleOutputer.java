package output.impl;

import analyzer.Configuration;
import fillers.Filler;
import output.Outputer;
import sorters.Sorter;

public class ConsoleOutputer implements Outputer {

    private static ConsoleOutputer instance;

    public static ConsoleOutputer getInstance(){
        if (instance == null){
            instance = new ConsoleOutputer();
        }
        return instance;
    }

    @Override
    public void outputResult() {
        for (Filler filler:Configuration.getListOfFillers()) {
            outputNameOfFiller(filler);
            outputHeader();
            outputValues(filler);
            outputFooter();
        }
    }

    private void outputFooter() {
        printTableDivider();
        System.out.println();
    }

    private void printTableDivider() {
        System.out.println(new String(new char[calculateLengthOfHeader()]).replace("\0", "-"));
    }

    private int calculateLengthOfHeader(){
        int xxx = 10;
        for (Sorter sorter: Configuration.getListOfSorters()) {
            xxx +=sorter.toString().length()+5;
        }
        return xxx;
    }

    private void outputHeader(){
        printTableDivider();
        System.out.printf("%-10s","Size");
        for (Sorter sorter: Configuration.getListOfSorters()) {
            System.out.printf("%-".concat(String.valueOf(sorter.toString().length()+5)).concat("s"), sorter.toString());
        }
        System.out.println();
        printTableDivider();
    }

    private void outputValues(Filler filler){
        for (Integer arraySize: Configuration.getListOfArraysSize()) {
            System.out.printf("%-10s", arraySize);
            for (Sorter sorter: Configuration.getListOfSorters()) {
                System.out.printf("%-".concat(String.valueOf(sorter.toString().length()+5)).concat("s"), sorter.getRecord(arraySize, filler));
            }
            System.out.println();
        }
    }

    private void outputNameOfFiller(Filler filler){
        System.out.println(filler.toString().toUpperCase());
    }

}
