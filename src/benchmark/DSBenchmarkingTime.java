package benchmark;

import data.Data;
import operations.OPArrayList;
import operations.OPLinkedList;

import java.util.List;
import java.util.function.BiFunction;

/** A class for timing the operation classes. */
public class DSBenchmarkingTime {

	public static void main(String [] args) {
		DSBenchmarking dsBenchmarking = new DSBenchmarking(200000);
		dsBenchmarking.measureRunTime();
	}
	
}
