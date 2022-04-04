package data;

import java.util.*;
import java.util.stream.Collectors;

/** A class that represents a data file. */
public abstract class Data {

	private List<Integer> integerList;
	private Map<String, Integer> map;

	private List<Integer> randomListSet;
	private Map<String, Integer> randomMapSet;

	/** Create a new data obj from the given list. */
	protected Data(List<Integer> integerList) {
		this.integerList = integerList;
		randomListSet = generateRandomListSet(getListData(), 100);
	}

	protected Data(Map<String, Integer> map) {
		this.map = map;
		randomMapSet = generateRandomMapSet(getMapData(), 100);
	}

	public abstract List<Integer> getDataStructure(); //you don't really need this, but anyway

	/** Perform process operation on the relevant data structure. */
	public void process() {
		//insertValues();
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
	public abstract void searchValuesMethod1(); //contains()
	public abstract void searchValuesMethod2(); //indexOf()
	/** Perform the get operation on the relevant data structure. */
	public abstract void getValues();
	/** Perform the delete operation on the relevant data structure. */
	public abstract void deleteValues();
	
	/** Return the entire data list of this data obj */
	public List<Integer> getListData() {
		return this.integerList;
	}

	/** Return the entire map of this data obj */
	public Map<String, Integer> getMapData() {
		return this.map;
	}

	/** Return the randomly selected data list from the original list */
	public List<Integer> getRandomListSet() {
		return randomListSet;
	}

	/** Return the randomly selected data list from the original list */
	public Map<String, Integer> getRandomMapSet() {
		return randomMapSet;
	}

	/** Returns a random sample data list. */
	public List<Integer> generateRandomListSet(List<Integer> stream, int fraction) {
		int i; // index for elements in stream[]

		// reservoir[] is the output array. Initialize it
		// with first k elements from stream[]
		int k = Math.round(stream.size()/fraction);
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

	/** Returns a random sample data list. */
	public Map<String, Integer> generateRandomMapSet(Map<String, Integer> initialMap, int fraction) {

		Set<String> keySet = initialMap.keySet();
		List<String> stream = new ArrayList<>(keySet);

		int i; // index for elements in stream[]

		// reservoir[] is the output array. Initialize it
		// with first k elements from stream[]
		int k = Math.round(stream.size()/fraction);
		String[] reservoir = new String[k];
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

		Map<String, Integer> returnMap = new HashMap<>();
		for(String keyVal: reservoir) {
			returnMap.put(keyVal, initialMap.get(keyVal));
		}

		return returnMap;
	}
}
