package benchmark;

import data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class DSBenchmarkMap extends DSBenchmark {

    public DSBenchmarkMap() { }

    public DSBenchmarkMap(int trials, int start, int increment, int steps, String filename) {
        super(trials, start, increment, steps, filename);
    }

    @Override
    public void measureRunTime() {
        List<String[]> outputList = getOutputList();

        long startTime = System.nanoTime();
        for (int numToCheck = getSTART(); numToCheck < getNUM_STEPS()*getINCREMENT() + getSTART(); numToCheck += getINCREMENT()) {

            StringBuilder output = new StringBuilder();

            Map<String, Integer> fromFile = getMapFromFile(getDATA_FILE(), numToCheck);

            int nameIndex = 0;
            for(BiFunction<Map<String, Integer>, Integer, Data> creator: getExperimentCreators_map()) {
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
                output.append("\t").append(numToCheck).append(",").append(getDataStructureName(nameIndex)).append(",");
                for(long[] function: startAndEndTimes) {
                    double estTime = (function[1] - function[0]) / (getTRIALS() * 1_000_000.0); //milliseconds
                    output.append(Math.round(estTime * 100.0)/100.0).append(",");
                }
                nameIndex++;
            }
            String[] string_array = Pattern.compile("\t").split(output.toString());
            for(String dataStr: string_array) {
                String[] data_array = Pattern.compile(",").split(dataStr);
                outputList.add(data_array);
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Total time(min): " + (endTime-startTime)/(1_000_000_000.0 * 60));

        WriteOutput wo = new WriteOutput();
        wo.writeToCsv(outputList, getFilename());
    }

    @Override
    public void measureMemoryConsumption() {
        List<String[]> outputList = getOutputList();

        long startTime = System.nanoTime();
        for (int numToCheck = getSTART(); numToCheck < getNUM_STEPS()*getINCREMENT() + getSTART(); numToCheck += getINCREMENT()) {

            StringBuilder output = new StringBuilder();

            Map<String, Integer> fromFile = getMapFromFile(getDATA_FILE(), numToCheck);

            int nameIndex = 0;
            for(BiFunction<Map<String, Integer>, Integer, Data> creator: getExperimentCreators_map()) {
                long[] basicMemoryConsumption = new long[6];
                for (int i = 0; i < getTRIALS(); i++) {
                    Data d = creator.apply(fromFile, getInitialCapacity());

                    //System.out.println(d.);

                    d.insertValues();
                    basicMemoryConsumption[0] += calcMem();
                    //basicMemoryConsumption[0] += getObjectSize(d.getDataStructure());

                    d.insertValuesAt();
                    basicMemoryConsumption[1] += calcMem();
                    //basicMemoryConsumption[1] += getObjectSize(d.getDataStructure());

                    d.searchValuesMethod1();
                    basicMemoryConsumption[2] += calcMem();
                    //basicMemoryConsumption[2] += getObjectSize(d.getDataStructure());

                    d.searchValuesMethod2();
                    basicMemoryConsumption[3] += calcMem();
                    //basicMemoryConsumption[3] += getObjectSize(d.getDataStructure());

                    d.getValues();
                    basicMemoryConsumption[4] += calcMem();
                    //basicMemoryConsumption[4] += getObjectSize(d.getDataStructure());

                    d.deleteValues();
                    basicMemoryConsumption[5] += calcMem();
                    //basicMemoryConsumption[5] += getObjectSize(d.getDataStructure());
                }
                output.append("\t").append(numToCheck).append(",").append(getDataStructureName(nameIndex)).append(",");
                for(long memVal: basicMemoryConsumption) {
                    double estMem = (memVal) / (double) getTRIALS();
                    output.append(Math.round(estMem / (1024 * 1024))).append(",");
                }
                nameIndex++;
            }
            String[] string_array = Pattern.compile("\t").split(output.toString());
            for(String dataStr: string_array) {
                String[] data_array = Pattern.compile(",").split(dataStr);
                outputList.add(data_array);
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Total time(min): " + (endTime-startTime)/(1_000_000_000.0 * 60));

        WriteOutput wo = new WriteOutput();
        wo.writeToCsv(outputList, getFilename());
    }

    @Override
    protected List<String[]> getOutputList() {
        List<String[]> outputList = new ArrayList<>();
        outputList.add(new String[] { //csv parameters
                "NumberOfIntegers",
                "DataStructure",
                "put()",
                "replace()",
                "containsKey()",
                "containsValue()",
                "get()",
                "remove()",
        });

        return outputList;
    }

    @Override
    protected String getDataStructureName(int index) {
        String[] names = {"HashMap", "HashMap_Initialized", "LinkedHashMap", "LinkedHashMap_Initialized", "TreeMap"};
        return names[index];
    }
}
