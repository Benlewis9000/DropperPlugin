package io.benlewis.dropin.dropper.map;

import io.benlewis.dropin.DropIn;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class DropperMapFactory {

    private final DropIn plugin;

    public DropperMapFactory(DropIn plugin){
        this.plugin = plugin;
    }

    public DropperMap create(String name, ConfigurationSection config){
        ConfigurationSection spawnConfig = config.getConfigurationSection("spawn");
        if (spawnConfig == null){
            throw new IllegalArgumentException("Could not find data for map spawn location in configuration.");
        }
        ConfigurationSection exitConfigPoint1 = config.getConfigurationSection("exit.point1");
        if (exitConfigPoint1 == null){
            throw new IllegalArgumentException("Could not find data for map exit region point1 in configuration..");
        }
        ConfigurationSection exitConfigPoint2 = config.getConfigurationSection("exit.point2");
        if (exitConfigPoint2 == null){
            throw new IllegalArgumentException("Could not find data for map exit region point2 in configuration..");
        }
        Location spawn = createLocation(spawnConfig);
        Location exitPoint1 = createLocation(exitConfigPoint1);
        Location exitPoint2 = createLocation(exitConfigPoint2);
        LocationCube exitRegion = new LocationCube(exitPoint1, exitPoint2);
        return new DropperMap(name, spawn, exitRegion);
    }

    public DropperMap create(String name, Location spawnLocation, LocationCube exitRegion){
        return new DropperMap(name, spawnLocation, exitRegion);
    }

    private Location createLocation(ConfigurationSection config) {
        String worldName = config.getString("world");
        World world = plugin.getServer().getWorld(worldName);
        if (world == null) {
            throw new IllegalArgumentException("Could not find world named \"%s\".".formatted(worldName));
        }
        return new Location(
                world,
                config.getDouble("x"),
                config.getDouble("y"),
                config.getDouble("z"),
                (float) config.getDouble("yaw"),
                (float) config.getDouble("pitch")
        );
    }

}
