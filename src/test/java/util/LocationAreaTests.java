package util;

import be.seeseemelk.mockbukkit.WorldMock;
import io.benlewis.dropin.util.LocationArea;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationAreaTests {

    @Test
    public void Collides_ShouldCollide(){
        World world = new WorldMock();
        Location point1 = new Location(world, 0.0, 0.0, 0.0);
        Location point2 = new Location(world, 2.0, 2.0, 2.0);
        Location location = new Location(world, 1.0, 1.0, 1.0);
        LocationArea locationArea = new LocationArea(point1, point2);
        assertTrue(locationArea.collides(location));
        LocationArea inverseLocationArea = new LocationArea(point2, point1);
        assertTrue(inverseLocationArea.collides(location));
    }

    @ParameterizedTest
    @MethodSource("provideNonCollidingPoints")
    public void Collides_ShouldNotCollide(double x2, double y2, double z2){
        World world = new WorldMock();
        Location point1 = new Location(world, 0.0, 0.0, 0.0);
        Location point2 = new Location(world, x2, y2, z2);
        Location location = new Location(world, 1.0, 1.0, 1.0);
        LocationArea locationArea = new LocationArea(point1, point2);
        assertFalse(locationArea.collides(location));
        LocationArea inverseLocationArea = new LocationArea(point2, point1);
        assertFalse(inverseLocationArea.collides(location));
    }

    private static Stream<Arguments> provideNonCollidingPoints(){
        return Stream.of(
                Arguments.of(1.0, 1.0, 1.0),
                Arguments.of(-2.0, 2.0, 2.0),
                Arguments.of(2.0, -2.0, 2.0),
                Arguments.of(2.0, 2.0, -2.0)
        );
    }

}
