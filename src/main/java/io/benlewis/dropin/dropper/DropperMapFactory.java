package io.benlewis.dropin.dropper;

import io.benlewis.dropin.util.LocationCube;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class DropperMapFactory {

    private final DropperMapManager dropperMapManager;

    public DropperMapFactory(DropperMapManager dropperMapManager){
        this.dropperMapManager = dropperMapManager;
    }

    public DropperMap create(ConfigurationSection config){
        // TODO
        throw new UnsupportedOperationException();
    }

    public DropperMap create(String name, Location spawnLocation, LocationCube exitRegion){
        DropperMap map = new DropperMap(name, spawnLocation, exitRegion);
        dropperMapManager.put(map.getName(), map);
        return map;
    }

}
