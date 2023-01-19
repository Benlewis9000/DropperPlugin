package io.benlewis.dropin.dropper;

import io.benlewis.dropin.util.LocationArea;
import org.bukkit.Location;

public class DropperMap {

    private final Location spawnLocation;
    private final LocationArea exitArea;

    // TODO make private, build with factory. Currently public for tests.
    public DropperMap(Location spawnLocation, LocationArea exitArea){
        this.spawnLocation = spawnLocation;
        this.exitArea = exitArea;
    }

    public Location getSpawnLocation(){
        return spawnLocation;
    }

    public LocationArea getExitArea(){
        return exitArea;
    }

}
