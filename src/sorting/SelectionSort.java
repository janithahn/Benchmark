/* 
 * Implementation of selection sort
 * Best case - O(n^2)
 * Average case - O(n^2)
 * Worse case - O(n^2)
 */
package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class SelectionSort {
	public static int[] selectionSort(int[] values) {
	    int currentIndex = 0;
		while (currentIndex < values.length) {
			int minValueIndex = currentIndex;
			for (int i = currentIndex; i < values.length; i++) {
				if (values[i] < values[minValueIndex]) {
					minValueIndex = i;
				}
			}
			swap(values, currentIndex, minValueIndex);
			currentIndex++;
		}
		return values;
	}
	
	public static void swap(int[] values, int currentIndex, int minIndex) {
		int temp = values[minIndex];
		values[minIndex] = values[currentIndex];
		values[currentIndex] = temp;
	}

	//Using List
	public static List<Integer> selectionSort(List<Integer> values) {
		int currentIndex = 0;
		while (currentIndex < values.size()) {
			int minValueIndex = currentIndex;
			for (int i = currentIndex; i < values.size(); i++) {
				if (values.get(i) < values.get(minValueIndex)) {
					minValueIndex = i;
				}
			}
			swap(values, currentIndex, minValueIndex);
			currentIndex++;
		}
		return values;
	}

	public static void swap(List<Integer> values, int currentIndex, int minIndex) {
		Collections.swap(values, minIndex, currentIndex);
	}


	public static void main(String args[]) {
		int[] values = { 4, 7, 2, 10, 1, 8 };
		int[] sortedValues = selectionSort(values);

		ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(4, 7, 2, 10, 1, 8));
		arrayList = (ArrayList<Integer>) selectionSort(arrayList);

		System.out.println(arrayList);

		/*for (int i : sortedValues) {
			System.out.println(i);
		}*/
	}	
}