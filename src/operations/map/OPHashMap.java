package operations.map;

import data.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OPHashMap extends Data {

    private final Map<String, Integer> hashMap;

    /**
     * Create a new data obj from the given list.
     *
     * @param map
     */
    public OPHashMap(Map<String, Integer> map) {
        super(map);
        hashMap = new HashMap<>();
    }

    public OPHashMap(Map<String, Integer> map, int initialCapacity) {
        super(map);
        hashMap = new HashMap<>(initialCapacity);
    }

    @Override
    public List<Integer> getDataStructure() {
        return null;
    }

    @Override
    public void insertValues() {
        for (Map.Entry<String,Integer> entry: getMapData().entrySet())
            hashMap.put(entry.getKey(), entry.getValue());
    }

    @Override
    public void insertValuesAt() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            hashMap.replace(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void searchValuesMethod1() { //containsKey
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            hashMap.containsKey(entry.getKey());
        }
    }

    @Override
    public void searchValuesMethod2() { //containsValue
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            hashMap.containsValue(entry.getValue());
        }
    }

    @Override
    public void getValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            hashMap.get(entry.getKey());
        }
    }

    @Override
    public void deleteValues() {
        for(Map.Entry<String,Integer> entry: getRandomMapSet().entrySet()) {
            hashMap.remove(entry.getKey());
        }
    }
}
