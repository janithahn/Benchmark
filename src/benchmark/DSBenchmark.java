package benchmark;

import data.Data;
import operations.list.OPArrayList;
import operations.list.OPLinkedList;
import operations.list.OPVector;
import operations.map.OPHashMap;
import operations.map.OPLinkedHashMap;
import operations.map.OPTreeMap;

import java.io.File;
import java.util.*;
import java.util.function.BiFunction;

public abstract class DSBenchmark {

    // Run each test more than once to get bigger numbers and less noise.
    private static int TRIALS = 1;

    // The data to test on
    private static final String DATA_FILE = "n-2^20-0.data";

    // The amount of characters to increment each step
    private static int INCREMENT = 50000;

    // The number of steps to run.
    private static int NUM_STEPS = 5;

    // The number of characters to start with.
    private static int START = 100000;

    // Set an initial capacity to the data structure in use
    // private int initialCapacity = 0;

    // Set a name to the output CSV file
    private String filename = "output_data";

    // This provides the list of operations to run for list datastructures
    private static final List<BiFunction<List<Integer>, Integer, Data>> experimentCreators_list = List.of(
            (data, capacity) -> new OPArrayList(data),
            OPArrayList::new,
            (data, capacity) -> new OPLinkedList(data),
            (data, capacity) -> new OPVector(data),
            OPVector::new
    );

    // This provides the list of operations to run for map datastructures
    private static final List<BiFunction<Map<String, Integer>, Integer, Data>> experimentCreators_map = List.of(
            (data, capacity) -> new OPHashMap(data),
            OPHashMap::new,
            (data, capacity) -> new OPLinkedHashMap(data),
            OPLinkedHashMap::new,
            (data, capacity) -> new OPTreeMap(data)
    );

    public DSBenchmark() { }

    public DSBenchmark(int trials, int start, int increment, int steps, String filename) {
        START = start;
        INCREMENT = increment;
        TRIALS = trials;
        NUM_STEPS = steps;
        this.filename = filename;
    }

    protected static int getTRIALS() {
        return TRIALS;
    }

    protected static String getDATA_FILE() {
        return DATA_FILE;
    }

    protected static int getINCREMENT() {
        return INCREMENT;
    }

    protected static int getNUM_STEPS() {
        return NUM_STEPS;
    }

    protected static int getSTART() {
        return START;
    }

    protected static List<BiFunction<List<Integer>, Integer, Data>> getExperimentCreators_list() { return experimentCreators_list; }

    protected static List<BiFunction<Map<String, Integer>, Integer, Data>> getExperimentCreators_map() { return experimentCreators_map; }

    protected int getInitialCapacity() {
        return getSTART() + ((getNUM_STEPS() - 1) * getINCREMENT());
    }

    protected String getFilename() { return filename; }

    /** Get the list of integers from the data file. */
    protected static List<Integer> getIntegerListFromFile(String filePath, int numToCheck) {
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

    /** Get the map from the data file. */
    protected static Map<String, Integer> getMapFromFile(String filePath, int numToCheck) {
        Map<String, Integer> mapOfIntegers = new HashMap<>();

        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext() && mapOfIntegers.size() < numToCheck){
                mapOfIntegers.put(getUUID(), Integer.parseInt(s.next()));
            }
        }
        catch(Exception e) {
            System.err.println(e);
            System.exit(0);
        }

        return mapOfIntegers;
    }

    /** Calculate memory consumption of each process. */
    protected static long calcMem() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        return (runtime.totalMemory() - runtime.freeMemory());
    }

    /** Return the randomly generated UUID to use as keys for the map data structures */
    private static String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public abstract void measureRunTime();

    public  abstract void measureMemoryConsumption();

    /** Initialized list for the data to be added before it gets written into csv. */
    protected abstract List<String[]> getOutputList();

    protected abstract String getDataStructureName(int index);
}
