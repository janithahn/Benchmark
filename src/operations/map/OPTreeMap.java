package operations.map;

import data.Data;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OPTreeMap extends Data {

    private final Map<String, Integer> treeMap;

    public OPTreeMap(Map<String, Integer> map) {
        super(map);
        treeMap = new TreeMap<>();
    }

    @Override
    public List<Integer> getDataStructure() {
        return null;
    }

    @Override
    public void insertValues() {
        for (Map.Entry<String,Integer> entry: getMapData().entrySet())
            treeMap.put(entry.getKey(), entry.getValue());
    }

    @Override
    public void insertValuesAt() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            treeMap.replace(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void searchValuesMethod1() { //containsKey
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            treeMap.containsKey(entry.getKey());
        }
    }

    @Override
    public void searchValuesMethod2() { //containsValue
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            treeMap.containsValue(entry.getValue());
        }
    }

    @Override
    public void getValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            treeMap.get(entry.getKey());
        }
    }

    @Override
    public void deleteValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            treeMap.remove(entry.getKey());
        }
    }
}
