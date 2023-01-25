package io.benlewis.dropin.dropper.map;

import io.benlewis.dropin.MockBukkitTest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DropperMapFactoryTests extends MockBukkitTest {

    @Override
    @BeforeEach
    protected void setUpBukkit(){
        super.setUpBukkit();
        server.addSimpleWorld("world");
    }

    @Test
    public void create_ShouldSuccessfullyCreateMap() throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(GOOD_MAP_CONFIG_DATA);
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin);
        DropperMap map = dropperMapFactory.create("example", mapConfig);
        assertEquals("example", map.getName());
        assertEquals(250, map.getSpawnLocation().getY());
    }

    private ConfigurationSection loadConfigFromString(String data) throws InvalidConfigurationException {
        YamlConfiguration config = new YamlConfiguration();
        config.loadFromString(data);
        return config;
    }

    private static final String GOOD_MAP_CONFIG_DATA = """
            spawn:
              x: 100
              y: 250
              z: -40
              pitch: 0
              yaw: 0
              world: world
            exit:
              point1:
                x: 90
                y: 10
                z: 0
                world: world
              point2:
                x: 90
                y: 14
                z: 3
                world: world""";

}
