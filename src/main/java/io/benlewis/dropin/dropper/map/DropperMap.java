package io.benlewis.dropin.dropper.map;

import org.bukkit.Location;

public class DropperMap {

    private final String name;
    private final Location spawnLocation;
    private final LocationCube exitRegion;

    protected DropperMap(String name, Location spawnLocation, LocationCube exitRegion){
        this.name = name;
        this.spawnLocation = spawnLocation;
        this.exitRegion = exitRegion;
    }

    public String getName() {
        return name;
    }

    public Location getSpawnLocation(){
        return spawnLocation;
    }

    public LocationCube getExitRegion(){
        return exitRegion;
    }

}
