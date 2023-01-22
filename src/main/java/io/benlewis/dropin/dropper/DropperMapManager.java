package io.benlewis.dropin.dropper;

import java.util.HashMap;
import java.util.Map;

public class DropperMapManager implements Manager<String, DropperMap>{

    private final Map<String, DropperMap> dropperMaps;

    public DropperMapManager(){
        this.dropperMaps = new HashMap<>();
    }

    @Override
    public void put(String mapName, DropperMap map) {
        dropperMaps.put(mapName, map);
    }

    @Override
    public boolean contains(String mapName) {
        return dropperMaps.containsKey(mapName);
    }

    @Override
    public DropperMap get(String mapName) {
        return dropperMaps.get(mapName);
    }

    @Override
    public void remove(String mapName) {
        dropperMaps.remove(mapName);
    }
}
