package data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/** A class that represents a data file. */
public abstract class Data {

	private final List<Integer> integerList;

	/** Create a new data obj from the given text. */
	protected Data(List<Integer> integerList)
	{
		this.integerList = integerList;
	}

	/**
	 * Perform process operation on the relevant data structure.
	 */
	public void process() {
		insertValues();
		//insertValuesAt();
		//searchValuesMethod1();
		//searchValuesMethod2();
		//getValues();
		//deleteValues();
	}

	/** Perform the insert operation on the relevant data structure. */
	public abstract void insertValues();
	public abstract void insertValuesAt();
	
	/** Perform the search operation on the relevant data structure. */
	public abstract void searchValuesMethod1();
	public abstract void searchValuesMethod2();

	/** Perform the get operation on the relevant data structure. */
	public abstract void getValues();
	
	/** Perform the delete operation on the relevant data structure. */
	public abstract void deleteValues();
	
	/** Return the entire data list of this data obj */
	public List<Integer> getData()
	{
		return this.integerList;
	}

	/** Returns a random sample data list. */
	public List<Integer> getRandomDataSet(List<Integer> stream, int fraction) {
		int i; // index for elements in stream[]

		// reservoir[] is the output array. Initialize it
		// with first k elements from stream[]
		int k = (int) Math.round(stream.size()/fraction);
		int[] reservoir = new int[k];
		for (i = 0; i < k; i++)
			reservoir[i] = stream.get(i);

		Random r = new Random();

		// Iterate from the (k+1)th element to nth element
		for (; i < stream.size(); i++) {
			// Pick a random index from 0 to i.
			int j = r.nextInt(i + 1);

			// If the randomly  picked index is smaller than
			// k, then replace the element present at the
			// index with new element from stream
			if (j < k)
				reservoir[j] = stream.get(i);
		}

		return Arrays.stream(reservoir).boxed().collect(Collectors.toList());
	}
	
}
