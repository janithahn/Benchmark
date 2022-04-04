import benchmark.DSBenchmark;
import benchmark.DSBenchmarkList;
import benchmark.DSBenchmarkMap;

public class App {
    public static void main(String[] args) {
        if(args.length == 7) {
            DSBenchmark dsBenchmark;

            if(args[0].equals("-list")) {
                try {
                    dsBenchmark = new DSBenchmarkList(
                            Integer.parseInt(args[2]), //trials
                            Integer.parseInt(args[3]), //start
                            Integer.parseInt(args[4]), //increment
                            Integer.parseInt(args[5]), //filename
                            args[6]
                    );
                    if (args[1].equals("-r"))
                        dsBenchmark.measureRunTime();
                    else if (args[1].equals("-m"))
                        dsBenchmark.measureMemoryConsumption();
                    else
                        throw new RuntimeException("Use either '-r' or '-m' as the second argument");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else if(args[0].equals("-map")) {
                try {
                    dsBenchmark = new DSBenchmarkMap(
                            Integer.parseInt(args[2]), //trials
                            Integer.parseInt(args[3]), //start
                            Integer.parseInt(args[4]), //increment
                            Integer.parseInt(args[5]), //filename
                            args[6]
                    );
                    if (args[1].equals("-r"))
                        dsBenchmark.measureRunTime();
                    else if (args[1].equals("-m"))
                        dsBenchmark.measureMemoryConsumption();
                    else
                        throw new RuntimeException("Use either '-r' or '-m' as the second argument");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else
                throw new RuntimeException("Use either '-list' or '-map' as the first argument");
        }else {
            System.err.println("Put the correct arguments and run again!");
        }
    }
}
