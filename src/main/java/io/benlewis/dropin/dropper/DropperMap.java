package io.benlewis.dropin.dropper;

import io.benlewis.dropin.util.LocationArea;
import org.bukkit.Location;

public class DropperMap {

    private final Location spawnLocation;
    private final LocationArea exitArea;

    protected DropperMap(Location spawnLocation, LocationArea exitArea){
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
