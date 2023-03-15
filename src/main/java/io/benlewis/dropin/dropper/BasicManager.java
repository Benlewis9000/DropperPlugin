package io.benlewis.dropin.dropper;

import java.util.HashMap;

public class BasicManager<K, V> implements Manager<K, V> {

    private final HashMap<K, V> map;

    public BasicManager(){
        this.map = new HashMap<>();
    }

    public void put(K id, V value) {
        map.put(id, value);
    }

    public boolean contains(K id) {
        return map.containsKey(id);
    }

    public V get(K id) {
        return map.get(id);
    }

    public void remove(K id) {
        map.remove(id);
    }

}
