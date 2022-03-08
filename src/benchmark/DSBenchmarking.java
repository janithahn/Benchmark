package benchmark;

import data.Data;
import operations.OPArrayList;
import operations.OPLinkedList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class DSBenchmarking {

    // Run each test more than once to get bigger numbers and less noise.
    private static final int TRIALS = 1;

    // The data to test on
    private static final String DATA_FILE = "data/random_numbers/generated-data/n-2^20-0.data";

    // The amount of characters to increment each step
    private static final int INCREMENT = 10000;

    // The number of steps to run.
    private static final int NUM_STEPS = 20;

    // The number of characters to start with.
    private static final int START = 10000;

    // Set an initial capacity to the data structure in use
    private int initialCapacity = 0;

    // This provides the list of operations to run
    private static final List<BiFunction<List<Integer>, Integer, Data>> experimentCreators = List.of(
            (data, capacity) -> new OPArrayList(data),
            OPArrayList::new,
            (data, capacity) -> new OPLinkedList(data)
    );

    public DSBenchmarking() {}

    public DSBenchmarking(int initialCapacity) { this.initialCapacity = initialCapacity; }

    /** Get the list of integers from the data file. */
    public static List<Integer> getIntegerListFromFile(String filePath, int numToCheck) {
        ArrayList<Integer> listOfIntegers = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext() && listOfIntegers.size() < numToCheck){
                listOfIntegers.add(Integer.parseInt(s.next()));
            }
        }
        catch(Exception e) {
            System.err.println(e);
            System.exit(0);
        }


        return listOfIntegers;
    }

    public static int getTRIALS() {
        return TRIALS;
    }

    public static String getDATA_FILE() {
        return DATA_FILE;
    }

    public static int getINCREMENT() {
        return INCREMENT;
    }

    public static int getNUM_STEPS() {
        return NUM_STEPS;
    }

    public static int getSTART() {
        return START;
    }

    public static List<BiFunction<List<Integer>, Integer, Data>> getExperimentCreators() {
        return experimentCreators;
    }

    public int getInitialCapacity() { return this.initialCapacity; }

    public void measureRunTime() {
        System.out.println("NumberOfIntegers\tArrayList\tLinkedList");
        for (int numToCheck = getSTART(); numToCheck < getNUM_STEPS()*getINCREMENT() + getSTART(); numToCheck += getINCREMENT()) {

            String output = numToCheck + "\t";

            List<Integer> fromFile = getIntegerListFromFile(getDATA_FILE(), numToCheck);

            for(BiFunction<List<Integer>, Integer, Data> creator: getExperimentCreators()) {
                long startTime = System.nanoTime();
                for (int i = 0; i < getTRIALS(); i++) {
                    Data d = creator.apply(fromFile, getInitialCapacity());
                    d.process();
                }
                long endTime = System.nanoTime();
                double estTime = (endTime - startTime) / 100000000.0;
                output += estTime + "\t";
            }

            System.out.println(output);

        }
    }

    public void measureMemoryConsumption() {
        System.out.println("NumberOfIntegers\tArrayList\tLinkedList");
        for (int numToCheck = getSTART(); numToCheck < getNUM_STEPS()*getINCREMENT() + getSTART(); numToCheck += getINCREMENT()) {

            String output = numToCheck + "\t";

            List<Integer> fromFile = getIntegerListFromFile(getDATA_FILE(), numToCheck);

            for(BiFunction<List<Integer>, Integer, Data> creator: getExperimentCreators()) {
                long basicMemoryConsumption = 0;
                for (int i = 0; i < getTRIALS(); i++) {
                    Data d = creator.apply(fromFile, getInitialCapacity());
                    d.process();
                    basicMemoryConsumption += calcMem();
                }
                double estMem = (basicMemoryConsumption) / (double) getTRIALS();
                output += Math.round(estMem/(1024 * 1024)) + "\t";
            }

            System.out.println(output);
        }
    }

    private static long calcMem() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        return memory;
    }
}