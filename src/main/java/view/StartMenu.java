package view;

import analyzer.Analyzer;
import analyzer.Configuration;
import fillers.Filler;
import fillers.impl.RandomArray;
import fillers.impl.SortedArray;
import fillers.impl.SortedArrayInDescendingOrder;
import fillers.impl.SortedArrayWithRandomLastElement;
import output.impl.CSVOutputer;
import output.impl.ConsoleOutputer;
import sorters.Sorter;
import sorters.impl.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StartMenu {

    // TODO: 12.11.2018  Refactor, reflection API

    private static final ArrayList<Sorter> listOfAllSorters = new ArrayList<>();
    private static final ArrayList<Filler> listOfAllFillers = new ArrayList<>();
    private static Scanner scanner;

    private static void init() {
        if (listOfAllFillers.isEmpty()) {
            listOfAllFillers.add(RandomArray.getInstance());
            listOfAllFillers.add(SortedArray.getInstance());
            listOfAllFillers.add(SortedArrayInDescendingOrder.getInstance());
            listOfAllFillers.add(SortedArrayWithRandomLastElement.getInstance());
        }
        if (listOfAllSorters.isEmpty()) {
            listOfAllSorters.add(BubbleSort.getInstance());
            listOfAllSorters.add(ReverseBubbleSort.getInstance());
            listOfAllSorters.add(SimpleSort.getInstance());
            listOfAllSorters.add(QuickSort.getInstance());
            listOfAllSorters.add(HalfDivideSort.getInstance(BubbleSort.getInstance()));
            listOfAllSorters.add(HalfDivideSort.getInstance(ReverseBubbleSort.getInstance()));
            listOfAllSorters.add(HalfDivideSort.getInstance(SimpleSort.getInstance()));
            listOfAllSorters.add(HalfDivideSort.getInstance(QuickSort.getInstance()));
        }
    }

    private static void setDefaultValues() {
        init();

        if (Configuration.getListOfSorters().isEmpty()) {
            for (Filler filler : listOfAllFillers) {
                Configuration.addFiller(filler);
            }
        }

        if (Configuration.getListOfSorters().isEmpty()) {
            for (Sorter sorter : listOfAllSorters) {
                Configuration.addSorter(sorter);
            }
        }

        if (Configuration.getListOfArraysSize().isEmpty()) {
            for (int i = 10; i < 60; i += 10) {
                Configuration.addArraySize(i);
            }
        }

        Configuration.setDefaultOrigin(0);
        Configuration.setDefaultBoundForRandom(20);
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Welcome ");
        showMenu();
        boolean menuFlag = true;
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    ConfigurationMenu();
                    showMenu();
                    break;
                }
                case "2": {
                    AnalyzeMenu();
                    showMenu();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showMenu();
            }
        }
    }

    private static void AnalyzeMenu() {
        setDefaultValues();
        Analyzer.analyze();
        boolean menuFlag = true;
        showAnalyzeMenu();
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    ConsoleOutputer.getInstance().outputResult();
                    showAnalyzeMenu();
                    break;
                }
                case "2": {
                    CSVOutputer.getInstance().outputResult();
                    showAnalyzeMenu();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showAnalyzeMenu();
            }
        }
    }

    private static void ConfigurationMenu() {
        init();
        boolean menuFlag = true;
        showConfigurationMenu();
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    changeSorters();
                    showConfigurationMenu();
                    break;
                }
                case "2": {
                    changeFillers();
                    showConfigurationMenu();
                    break;
                }
                case "3": {
                    changeArraySize();
                    showConfigurationMenu();
                    break;
                }
                case "4": {
                    setDefaultRandomOrigin();
                    break;
                }
                case "5": {
                    setDefaultRandomBound();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showConfigurationMenu();
            }
        }
    }

    private static void setDefaultRandomBound() {
        System.out.println("Now bound is: " + Configuration.getDefaultBoundForRandom());
        System.out.println("Enter bound");
        try {
            Configuration.setDefaultBoundForRandom(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e){
            showNotNumberMessage();
        }
        showConfigurationMenu();
    }

    private static void setDefaultRandomOrigin() {
        System.out.println("Now origin is: " + Configuration.getDefaultOrigin());
        System.out.println("Enter origin");
        try {
            Configuration.setDefaultOrigin(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e){
            showNotNumberMessage();
        }
        showConfigurationMenu();
    }

    private static void changeArraySize() {
        showAllSizeValues();
        boolean menuFlag = true;
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    System.out.println("Enter array size");
                    try {
                        Configuration.addArraySize(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    }
                    showAllSizeValues();
                    break;
                }
                case "2": {
                    System.out.println("Enter array size");
                    try {
                        Configuration.removeArraySize(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Wrong number");
                    }
                    showAllSizeValues();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showAllSizeValues();
            }
        }
    }

    private static void changeFillers() {
        showAllFillerValues();
        boolean menuFlag = true;
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    System.out.println("Enter index of possible filler");
                    try {
                        Configuration.addFiller(listOfAllFillers.get(Integer.parseInt(scanner.nextLine())));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    } catch (IndexOutOfBoundsException e) {
                        showNotIndexMessage();
                    }
                    showAllFillerValues();
                    break;
                }
                case "2": {
                    System.out.println("Enter index of used filler");
                    try {
                        Configuration.removeFiller(Configuration.getListOfFillers().get(Integer.parseInt(scanner.nextLine())));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    } catch (IndexOutOfBoundsException e) {
                        showNotIndexMessage();
                    }
                    showAllFillerValues();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showAllFillerValues();
            }
        }
    }

    private static void changeSorters() {
        showAllSorterValues();
        boolean menuFlag = true;
        while (menuFlag) {
            switch (scanner.nextLine()) {
                case "1": {
                    System.out.println("Enter index of possible sorter");
                    try {
                        Configuration.addSorter(listOfAllSorters.get(Integer.parseInt(scanner.nextLine())));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    } catch (IndexOutOfBoundsException e) {
                        showNotIndexMessage();
                    }
                    showAllSorterValues();
                    break;
                }
                case "2": {
                    System.out.println("Enter index of used sorter");
                    try {
                        Configuration.removeSorter(Configuration.getListOfSorters().get(Integer.parseInt(scanner.nextLine())));
                    } catch (NumberFormatException e){
                        showNotNumberMessage();
                    } catch (IndexOutOfBoundsException e) {
                        showNotIndexMessage();
                    }
                    showAllSorterValues();
                    break;
                }
                case "0": {
                    menuFlag = false;
                    break;
                }
                default:
                    showWrongMessage();
                    showAllSorterValues();
            }
        }
    }

    private static void showNotNumberMessage() {
        System.out.println("Only numbers allowed!");
    }

    private static void showNotIndexMessage() {
        System.out.println("Wrong index!");
    }

    private static void showAllSorterValues() {
        System.out.println("Possible sorters: " + listOfAllSorters.toString());
        System.out.println("Used sorters: " + Configuration.getListOfSorters().toString());
        showChangeMenu();
    }

    private static void showAllSizeValues() {
        System.out.println("Array sizes" + Configuration.getListOfArraysSize());
        showChangeMenu();
    }


    private static void showWrongMessage(){
        System.out.println("Wrong choose. Try again!");
    }

    private static void showMenu(){
        System.out.println("1. Change configuration.");
        System.out.println("2. Start analyzing.");
        System.out.println("0. Exit.");
    }

    private static void showConfigurationMenu(){
        System.out.println("1. Change sorters.");
        System.out.println("2. Change fillers.");
        System.out.println("3. Change array size.");
        System.out.println("4. Set default random origin.");
        System.out.println("5. Set default random bound.");
        System.out.println("0. Exit.");
    }

    private static void showChangeMenu(){
        System.out.println("1. Add.");
        System.out.println("2. Delete.");
        System.out.println("0. Exit.");
    }

    private static void showAllFillerValues() {
        System.out.println("Possible fillers: " + listOfAllFillers.toString());
        System.out.println("Used fillers: " + Configuration.getListOfFillers().toString());
        showChangeMenu();
    }

    private static void showAnalyzeMenu() {
        System.out.println("1. Output result in console.");
        System.out.println("2. Output result in CSV file.");
        System.out.println("0. Exit.");
    }
}
