package io.benlewis.dropin.dropper;

public interface Manager<K, V> {

    void put(K id, V value);

    boolean contains(K id);

    V get(K id);

    void remove(K id);

}
