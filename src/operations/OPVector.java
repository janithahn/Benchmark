package operations;

import data.Data;

import java.util.List;
import java.util.Vector;

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
    public void process() {
        insertValues();
        //searchValues();
        //deleteValues();
    }

    @Override
    public void insertValues() {
        for (Integer val:getData()) {
            vectorList.add(val);
        }
    }

    @Override
    public void searchValues() {
        int i = 0;
        for(Integer val: randomDataSet) {
            if(vectorList.contains(val)) {
                i++;
            }
        }
    }

    @Override
    public void deleteValues() {
        for(Integer val: randomDataSet) {
            vectorList.remove(val);
        }
    }
}
