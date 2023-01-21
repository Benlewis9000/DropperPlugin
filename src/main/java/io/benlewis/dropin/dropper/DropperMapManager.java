package io.benlewis.dropin.dropper;

public interface DropperMapManager {

    void add(String mapName, DropperMap map);

    boolean contains(String mapName);

    DropperMap get(String mapName);

    void remove(String mapName);

}
