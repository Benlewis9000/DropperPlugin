package io.benlewis.dropin.dropper.map;

import io.benlewis.dropin.MockBukkitTest;
import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.benlewis.dropin.testutils.ConfigurationUtils.loadConfigFromString;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DropperMapFactoryTests extends MockBukkitTest {

    @Override
    @BeforeEach
    protected void setUpBukkit() {
        super.setUpBukkit();
        server.addSimpleWorld("world");
    }

    @Test
    public void create_ShouldSuccessfullyCreateMap() throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(GOOD_MAP_CONFIG_DATA);
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin.getServer());
        DropperMap map = dropperMapFactory.create("testmap", mapConfig);
        assertEquals("testmap", map.getName());
        assertEquals(250, map.getSpawnLocation().getY());
    }

    @Test
    public void test_InvalidWorldName_ShouldThrowInvalidConfigurationException() throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(BAD_WORLD_MAP_CONFIG_DATA);
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin.getServer());

        Throwable thrown = catchThrowable(() -> dropperMapFactory.create("testmap", mapConfig));
        assertThat(thrown.getMessage()).containsIgnoringCase("could not find world named badworld");
    }

    @ParameterizedTest
    @MethodSource("provideMissingSectionTestData")
    public void test_MissingSection_ShouldThrowExceptionWithMessage(String sectionToRemove, String expectedMessage)
            throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(GOOD_MAP_CONFIG_DATA);
        mapConfig.set(sectionToRemove, null);
        var mapConfigTrimmed = mapConfig.getConfigurationSection("testmap");
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin.getServer());

        Throwable thrown = catchThrowable(() -> dropperMapFactory.create("testmap", mapConfigTrimmed));
        assertThat(thrown.getMessage()).containsIgnoringCase(expectedMessage);
    }

    private static Stream<Arguments> provideMissingSectionTestData(){
        return Stream.of(
          Arguments.of("testmap.spawn", "Could not find data for map spawn location in configuration"),
          Arguments.of("testmap.exit.point1", "Could not find data for map exit region point1 in configuration"),
          Arguments.of("testmap.exit.point2", "Could not find data for map exit region point2 in configuration"),
          Arguments.of("testmap.exit.point1.world", "Could not find a world name in"),
          Arguments.of("testmap.spawn.world", "Could not find a world name in")
        );
    }

    private static final String GOOD_MAP_CONFIG_DATA = """
          testmap:
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
                z: 0
                world: world""";

    private static final String BAD_WORLD_MAP_CONFIG_DATA = """
            spawn:
              x: 100
              y: 250
              z: -40
              pitch: 0
              yaw: 0
              world: badworld
            exit:
              point1:
                x: 90
                y: 10
                z: 0
                world: world
              point2:
                x: 90
                y: 14
                z: 0
                world: world""";

}
