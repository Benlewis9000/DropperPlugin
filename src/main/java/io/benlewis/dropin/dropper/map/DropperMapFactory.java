package io.benlewis.dropin.dropper.map;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;

public class DropperMapFactory {

    private final Server server;

    public DropperMapFactory(Server server){
        this.server = server;
    }

    public DropperMap create(String name, ConfigurationSection config) throws InvalidConfigurationException {
        ConfigurationSection spawnConfig = config.getConfigurationSection("spawn");
        if (spawnConfig == null){
            throw new InvalidConfigurationException("Could not find data for map spawn location in configuration.");
        }
        ConfigurationSection exitConfigPoint1 = config.getConfigurationSection("exit.point1");
        if (exitConfigPoint1 == null){
            throw new InvalidConfigurationException("Could not find data for map exit region point1 in configuration.");
        }
        ConfigurationSection exitConfigPoint2 = config.getConfigurationSection("exit.point2");
        if (exitConfigPoint2 == null){
            throw new InvalidConfigurationException("Could not find data for map exit region point2 in configuration.");
        }
        Location spawn = createLocation(spawnConfig);
        Location exitPoint1 = createLocation(exitConfigPoint1);
        Location exitPoint2 = createLocation(exitConfigPoint2);
        LocationCube exitRegion = new LocationCube(exitPoint1, exitPoint2);
        return new DropperMap(name, spawn, exitRegion);
    }

    private Location createLocation(ConfigurationSection config) throws InvalidConfigurationException {
        String worldName = config.getString("world");
        if (worldName == null){
            throw new InvalidConfigurationException("Could not find a world name in %s".formatted(config.getCurrentPath()));
        }
        World world = server.getWorld(worldName);
        if (world == null) {
            throw new InvalidConfigurationException("Could not find world named \"%s\" on the server specified in %s".formatted(worldName, config.getCurrentPath()));
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
