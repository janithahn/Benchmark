package operations;

import data.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList is,
 *  + Not synchronized (multiple threads)
 *  + Increments 50% of the current size when the number of elements exceeds its capacity.
 *  + Default capacity is 10.
 *  + Adding n elements requires O(n) time.
 *  + Constructor has the initialCapacity parameter [ArrayList(int initialCapacity)].
 *  + Internally uses an array.
 *  + Slower in terms of data manipulation tasks, but better for storing, and accessing data.
 *  + add() -> O(1)
 *    add(index, element) -> O(N)
 *    get() -> O(1)
 *    remove() -> O(N)
 *    indexOf() -> O(N)
 *    contains() -> O(N)
 * */
public class OPArrayList extends Data {

    /** ArrayList that all the operations are performed on. */
    private final ArrayList<Integer> arrayList;

    private final List<Integer> randomDataSet = getRandomDataSet(getData(), 100);

    public OPArrayList(List<Integer> integerList) {
        super(integerList);
        arrayList = new ArrayList<>();
        //process();
    }

    public OPArrayList(List<Integer> integerList, int initialCapacity) {
        super(integerList);
        arrayList = new ArrayList<>(initialCapacity);
        //process();
    }

    @Override
    public void process() {
        insertValues();
        //searchValues();
        //deleteValues();
    }

    @Override
    public void insertValues() {
        for (Integer val:getData()) {
            arrayList.add(val);
        }
    }

    @Override
    public void searchValues() {
        int i = 0;
        for(Integer val: randomDataSet) {
            if(arrayList.contains(val)) {
                i++;
            }
        }
    }

    @Override
    public void deleteValues() {
        for(Integer val: randomDataSet) {
            arrayList.remove(val);
        }
    }
}
