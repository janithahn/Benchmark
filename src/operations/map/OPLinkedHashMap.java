package operations.map;

import data.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OPLinkedHashMap extends Data {

    private final Map<String, Integer> linkedHashMap;

    public OPLinkedHashMap(Map<String, Integer> map) {
        super(map);
        linkedHashMap = new LinkedHashMap<>();
    }

    public OPLinkedHashMap(Map<String, Integer> map, int initialCapacity) {
        super(map);
        linkedHashMap = new LinkedHashMap<>(initialCapacity);
    }

    @Override
    public List<Integer> getDataStructure() {
        return null;
    }

    @Override
    public void insertValues() {
        for (Map.Entry<String,Integer> entry: getMapData().entrySet())
            linkedHashMap.put(entry.getKey(), entry.getValue());
    }

    @Override
    public void insertValuesAt() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            linkedHashMap.replace(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void searchValuesMethod1() { //containsKey
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            linkedHashMap.containsKey(entry.getKey());
        }
    }

    @Override
    public void searchValuesMethod2() { //containsValue
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            linkedHashMap.containsValue(entry.getValue());
        }
    }

    @Override
    public void getValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            linkedHashMap.get(entry.getKey());
        }
    }

    @Override
    public void deleteValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            linkedHashMap.remove(entry.getKey());
        }
    }
}
