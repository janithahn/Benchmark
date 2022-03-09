package operations;

import data.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList is,
 *  + Not synchronized (multiple threads)
 *  + Increments 50% of the current size when the number of elements exceeds its capacity.
 *  + Constructor does not have the initialCapacity parameter.
 *  + Internally uses a doubly linked list.
 *  + Better for data manipulation tasks.
 *  + add() -> O(1)
 *    add(index, element) -> O(N)
 *    get() -> O(N)
 *    remove() -> O(N)
 *    contains() -> O(N)
 * */

/** Class to perform operations on the LinkedList. */
public class OPLinkedList extends Data {

    /** LinkedList that all the operations are performed on. */
    private final LinkedList<Integer> linkedList;

    public OPLinkedList(List<Integer> integerList) {
        super(integerList);
        linkedList = new LinkedList<>();
        //process();
    }

    @Override
    public void insertValues() {
        for (Integer val:getData()) {
            linkedList.add(val);
        }
    }

    @Override
    public void insertValuesAt() {
        int i = 0;
        for (Integer val: getRandomDataSet()) {
            linkedList.add(i, val);
            i++;
        }
    }

    @Override
    public void searchValuesMethod1() {
        int i = 0;
        for(Integer val: getRandomDataSet()) {
            if(linkedList.contains(val)) {
                i++;
            }
        }
    }

    @Override
    public void searchValuesMethod2() {
        int i = -1;
        for(Integer val: getRandomDataSet()) {
            i = linkedList.indexOf(val);
        }
    }

    @Override
    public void getValues() {
        int val = 0;
        for(int i=0; i< getRandomDataSet().size(); i++) {
            val = linkedList.get(i);
        }
    }

    @Override
    public void deleteValues() {
        for(Integer val: getRandomDataSet()) {
            linkedList.remove(val);
        }
    }
}
