package benchmark;

/** A class for timing the operation classes. */
public class BenchmarkTime {

	public static void main(String [] args) {
		DSBenchmarkList dsBenchmarkList = new DSBenchmarkList();
		DSBenchmarkMap dsBenchmarkMap = new DSBenchmarkMap();

		//dsBenchmarkList.measureRunTime();
		dsBenchmarkMap.measureRunTime();
	}
	
}
