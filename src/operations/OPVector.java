package operations;

import data.Data;

import java.util.List;
import java.util.Vector;

/**
 * ArrayList is,
 *  + Synchronized (single thread)
 *  + Increments 100%(doubles the capacity) of the current size when the number of elements exceeds its capacity.
 *  + Constructor -> Vector(int initialCapacity, int capacityIncrement).
 *  + Capacity is always at least as large as the vector size.
 *  + Constructor has the initialCapacity parameter.
 *  + Can be used ** if size of the array is not known in advance **.
 *  + Access -> O(1)
 *    Adding and removing elements from the end -> O(1)
 * */

/** Class to perform operations on the Vector. */
public class OPVector extends Data {

    /** ArrayList that all the operations are performed on. */
    private final Vector<Integer> vectorList;

    private final List<Integer> randomDataSet = getRandomDataSet(getData(), 100);

    public OPVector(List<Integer> integerList) {
        super(integerList);
        vectorList = new Vector<>();
        //process();
    }

    public OPVector(List<Integer> integerList, int initialCapacity) {
        super(integerList);
        vectorList = new Vector<>(initialCapacity);
        //process();
    }

    @Override
    public void insertValues() {
        for (Integer val:getData()) {
            vectorList.add(val);
        }
    }

    @Override
    public void insertValuesAt() {
        int i = 0;
        for (Integer val: randomDataSet) {
            vectorList.add(i, val);
            i++;
        }
    }

    @Override
    public void searchValuesMethod1() {
        int i = 0;
        for(Integer val: randomDataSet) {
            if(vectorList.contains(val)) {
                i++;
            }
        }
    }

    @Override
    public void searchValuesMethod2() {
        int i = -1;
        for(Integer val: randomDataSet) {
            i = vectorList.indexOf(val);
        }
    }

    @Override
    public void getValues() {
        int val = 0;
        for(int i=0; i< randomDataSet.size(); i++) {
            val = vectorList.get(i);
        }
    }

    @Override
    public void deleteValues() {
        for(Integer val: randomDataSet) {
            vectorList.remove(val);
        }
    }
}
