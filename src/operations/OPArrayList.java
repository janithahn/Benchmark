package operations;

import data.Data;

import java.util.ArrayList;
import java.util.List;

public class OPArrayList extends Data {

    /** ArrayList that all the operations are performed on. */
    private final ArrayList<Integer> arrayList;

    private final List<Integer> randomDataSet = getRandomDataSet(getData(), 3);

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
        searchValues();
        deleteValues();
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
