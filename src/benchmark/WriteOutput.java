package benchmark;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class WriteOutput {
    public static List<String[]> memoryData = new ArrayList<>();
    public static List<String[]> runtimeData = new ArrayList<>();
    public static List<String[]> data = new ArrayList<>();

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void writeToCsv(List<String[]> outputList, String filename) {
        File csvOutputFile = new File("./output/" + filename + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            outputList.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assertTrue(csvOutputFile.exists());
    }
}
