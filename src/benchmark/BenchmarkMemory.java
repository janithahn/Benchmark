package benchmark;

/** A class for timing the operation classes. */
public class BenchmarkMemory {

	public static void main(String [] args) {
		DSBenchmarkList dsBenchmarkList = new DSBenchmarkList();
		DSBenchmarkMap dsBenchmarkMap = new DSBenchmarkMap();

		//dsBenchmarkList.measureMemoryConsumption();
		dsBenchmarkMap.measureMemoryConsumption();
	}
	
}
