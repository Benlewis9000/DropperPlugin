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
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin);
        DropperMap map = dropperMapFactory.create("testmap", mapConfig);
        assertEquals("testmap", map.getName());
        assertEquals(250, map.getSpawnLocation().getY());
    }

    @Test
    public void test_InvalidWorldName_ShouldThrowIllegalArgumentException() throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(BAD_WORLD_MAP_CONFIG_DATA);
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dropperMapFactory.create("testmap", mapConfig));
        assertExceptionMessageContains(ex, "could not find world named");
    }

    @ParameterizedTest
    @MethodSource("provideMissingSectionTestData")
    public void test_MissingSection_ShouldThrowIllegalArgumentException(String sectionToRemove, String exMessage)
            throws InvalidConfigurationException {
        var mapConfig = loadConfigFromString(GOOD_MAP_CONFIG_DATA);
        mapConfig.set(sectionToRemove, null);
        DropperMapFactory dropperMapFactory = new DropperMapFactory(plugin);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dropperMapFactory.create("testmap", mapConfig));
        assertExceptionMessageContains(ex, "could not find data for map %s".formatted(exMessage));
    }

    private static Stream<Arguments> provideMissingSectionTestData(){
        return Stream.of(
          Arguments.of("spawn", "spawn"),
          Arguments.of("exit.point1", "exit region point1"),
          Arguments.of("exit.point2", "exit region point2")
        );
    }

    private void assertExceptionMessageContains(Exception exception, String expected){
        assertTrue(exception.getMessage().toLowerCase().contains(expected));
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
