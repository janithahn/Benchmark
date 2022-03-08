package operations;

import data.Data;

import java.util.LinkedList;
import java.util.List;

public class OPLinkedList extends Data {

    /** LinkedList that all the operations are performed on. */
    private final LinkedList<Integer> linkedList;

    private final List<Integer> randomDataSet = getRandomDataSet(getData(), 3);

    public OPLinkedList(List<Integer> integerList) {
        super(integerList);
        linkedList = new LinkedList<>();
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
            linkedList.add(val);
        }
    }

    @Override
    public void searchValues() {
        int i = 0;
        for(Integer val: randomDataSet) {
            if(linkedList.contains(val)) {
                i++;
            }
        }
    }

    @Override
    public void deleteValues() {
        for(Integer val: randomDataSet) {
            linkedList.remove(val);
        }
    }
}
