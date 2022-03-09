package benchmark;

import data.Data;
import operations.OPArrayList;
import operations.OPLinkedList;
import operations.OPVector;

import java.io.File;
import java.net.StandardSocketOptions;
import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class DSBenchmarking {

    // Run each test more than once to get bigger numbers and less noise.
    private static final int TRIALS = 100;

    // The data to test on
    private static final String DATA_FILE = "data/random_numbers/generated-data/n-2^20-0.data";

    // The amount of characters to increment each step
    private static final int INCREMENT = 50000;

    // The number of steps to run.
    private static final int NUM_STEPS = 20;

    // The number of characters to start with.
    private static final int START = 100000;

    // Set an initial capacity to the data structure in use
    private int initialCapacity = 0;

    // This provides the list of operations to run
    private static final List<BiFunction<List<Integer>, Integer, Data>> experimentCreators = List.of(
            (data, capacity) -> new OPArrayList(data),
            //OPArrayList::new,
            (data, capacity) -> new OPLinkedList(data),
            (data, capacity) -> new OPVector(data)
            //OPVector::new
    );

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
        List<String[]> outputList = new ArrayList<>();
        outputList.add(new String[] { //csv parameters
                "NumberOfIntegers",
                "ArrayList_insertValues()",
                "ArrayList_insertValuesAt()",
                "ArrayList_searchValuesMethod1()",
                "ArrayList_searchValuesMethod2()",
                "ArrayList_getValues()",
                "ArrayList_getValues()",
                "LinkedList_insertValues()",
                "LinkedList_insertValuesAt()",
                "LinkedList_searchValuesMethod1()",
                "LinkedList_searchValuesMethod2()",
                "LinkedList_getValues()",
                "LinkedList_getValues()",
                "Vector_insertValues()",
                "Vector_insertValuesAt()",
                "Vector_searchValuesMethod1()",
                "Vector_searchValuesMethod2()",
                "Vector_getValues()",
                "Vector_getValues()",
        });

        //System.out.println("NumberOfIntegers\tArrayList\tLinkedList\tVector");
        long startTime = System.nanoTime();
        for (int numToCheck = getSTART(); numToCheck < getNUM_STEPS()*getINCREMENT() + getSTART(); numToCheck += getINCREMENT()) {

            String output = numToCheck + "\t";

            List<Integer> fromFile = getIntegerListFromFile(getDATA_FILE(), numToCheck);

            for(BiFunction<List<Integer>, Integer, Data> creator: getExperimentCreators()) {
                long[][] startAndEndTimes = new long[6][2];
                for (int i = 0; i < getTRIALS(); i++) {
                    Data d = creator.apply(fromFile, getInitialCapacity());

                    startAndEndTimes[0][0] += System.nanoTime();
                    d.insertValues();
                    startAndEndTimes[0][1] += System.nanoTime();

                    startAndEndTimes[1][0] += System.nanoTime();
                    d.insertValuesAt();
                    startAndEndTimes[1][1] += System.nanoTime();

                    startAndEndTimes[2][0] += System.nanoTime();
                    d.searchValuesMethod1();
                    startAndEndTimes[2][1] += System.nanoTime();

                    startAndEndTimes[3][0] += System.nanoTime();
                    d.searchValuesMethod2();
                    startAndEndTimes[3][1] += System.nanoTime();

                    startAndEndTimes[4][0] += System.nanoTime();
                    d.getValues();
                    startAndEndTimes[4][1] += System.nanoTime();

                    startAndEndTimes[5][0] += System.nanoTime();
                    d.deleteValues();
                    startAndEndTimes[5][1] += System.nanoTime();
                }
                for(long[] function: startAndEndTimes) {
                    double estTime = (function[1] - function[0]) / (getTRIALS() * 1_000_000.0); //milliseconds
                    output += estTime + "\t";
                }
                //double estTime = (endTime - startTime) / (getTRIALS() * 1_000_000.0); //milliseconds
                //output += estTime + "\t";
            }
            Pattern pattern = Pattern.compile("\t");
            String[] string_array = pattern.split(output);
            outputList.add(string_array);
            //System.out.println(output);
        }
        long endTime = System.nanoTime();

        System.out.println("Total time(min): " + (endTime-startTime)/(1_000_000_000.0 * 60));

        WriteOutput wo = new WriteOutput();
        wo.writeToCsv(outputList, "runtime_with_all_functions");
    }

    public void measureMemoryConsumption() {
        List<String[]> outputList = new ArrayList<>();
        outputList.add(new String[] {
                "NumberOfIntegers",
                "ArrayList",
                "ArrayList(Initialized)",
                "LinkedList",
                "Vector",
                "Vector(Initialized)"
        });

        System.out.println("NumberOfIntegers\tArrayList\tLinkedList\tVector");
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
            Pattern pattern = Pattern.compile("\t");
            String[] string_array = pattern.split(output);
            outputList.add(string_array);

            System.out.println(output);
        }

        WriteOutput wo = new WriteOutput();
        //wo.writeToCsv(outputList, "memory");
    }

    private static long calcMem() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        return memory;
    }
}
