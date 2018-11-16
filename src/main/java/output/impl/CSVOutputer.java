package output.impl;

import analyzer.Configuration;
import fillers.Filler;
import output.Outputer;
import sorters.Sorter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVOutputer implements Outputer {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_EXTENSION = ".csv";
    private static CSVOutputer instance;

    private FileWriter fileWriter = null;

    public static CSVOutputer getInstance(){
        if (instance == null){
            instance = new CSVOutputer();
        }
        return instance;
    }


    @Override
    public void outputResult() {
        fileWriter = null;
        try {
            fileWriter = new FileWriter(setFilePath());
            for (Filler filler:Configuration.getListOfFillers()) {
                outputNameOfFiller(filler);
                outputHeader();
                outputValues(filler);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            System.out.println("Error in creating/filling CSV file!!!");
//            e.printStackTrace();
        } finally {
            try {
                if (fileWriter!=null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while flushing/closing CSV file!!!");
//                e.printStackTrace();
            }

        }
    }

    private String setFilePath(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input file path:");
        String path = in.nextLine();
        if (!path.contains(FILE_EXTENSION)){
            return path.concat(FILE_EXTENSION);
        }
        return path;
    }

    private void outputHeader() throws IOException {
        fileWriter.append("Size");
        for (Sorter sorter: Configuration.getListOfSorters()) {
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(sorter.toString());
        }
        fileWriter.append(NEW_LINE_SEPARATOR);
    }

    private void outputValues(Filler filler) throws IOException {
        for (Integer arraySize: Configuration.getListOfArraysSize()) {
            fileWriter.append(arraySize.toString());
            for (Sorter sorter: Configuration.getListOfSorters()) {
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(sorter.getRecord(arraySize, filler)));
            }
            fileWriter.append(NEW_LINE_SEPARATOR);
        }
    }

    private void outputNameOfFiller(Filler filler) throws IOException {
        fileWriter.append(filler.toString().toUpperCase());
        fileWriter.append(NEW_LINE_SEPARATOR);
    }
}
