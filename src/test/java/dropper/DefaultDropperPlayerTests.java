package dropper;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.benlewis.dropin.DropIn;
import io.benlewis.dropin.dropper.*;
import io.benlewis.dropin.util.LocationCube;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DefaultDropperPlayerTests {

    private ServerMock server;
    private DropIn plugin;
    private Player player;

    private DropperPlayerManager playerManager;
    private DropperPlayerFactory playerFactory;
    private DropperMapFactory mapFactory;
    private List<DropperMap> maps;
    private Location map1SpawnLocation;
    private Location map2SpawnLocation;
    private DropperPlayer dropperPlayer;

    @BeforeEach
    public void setUpBukkit(){
        server = MockBukkit.mock();
        plugin = MockBukkit.load(DropIn.class);
        player = server.addPlayer();

        playerManager = new DropperPlayerManager();
        playerFactory = new DropperPlayerFactory(playerManager);
        DropperMapManager mapManager = new DropperMapManager();
        mapFactory = new DropperMapFactory(mapManager);

        maps = new ArrayList<>();
        World world = server.addSimpleWorld("world");
        map1SpawnLocation = new Location(world, 42, 100, 0);
        map2SpawnLocation = new Location(world, 7, 100, 0);
        DropperMap map1 = mapFactory.create("map1", map1SpawnLocation, mock(LocationCube.class));
        DropperMap map2 = mapFactory.create("map2", map2SpawnLocation, mock(LocationCube.class));
        maps.add(map1);
        maps.add(map2);
        MapRotation mapRotation = new LinearMapRotation(maps);
        dropperPlayer = playerFactory.create(player, mapRotation);
    }

    @AfterEach
    public void tearDownBukkit(){
        MockBukkit.unmock();
    }

    @Test
    public void startDropper_ShouldSetCurrentMap(){
        dropperPlayer.startDropper();
        assertEquals(maps.get(0), dropperPlayer.getCurrentMap());
    }

    @Test
    public void startDropper_ShouldTeleportToMapSpawn(){
        assertNotEquals(map1SpawnLocation, dropperPlayer.getLocation(),
                "player and map spawn should be different before player starts game");
        dropperPlayer.startDropper();
        assertEquals(map1SpawnLocation, dropperPlayer.getLocation());
    }

    @Test
    public void quitDropper_ShouldTeleportToPreMapLocation(){
        Location preStartLocation = player.getLocation();
        dropperPlayer.startDropper();
        assertNotEquals(preStartLocation, dropperPlayer.getLocation());
        dropperPlayer.quitDropper();
        assertEquals(preStartLocation, dropperPlayer.getLocation());
    }

    @Test
    public void quitDropper_ShouldRemovePlayerFromManager(){
        dropperPlayer.startDropper();
        assertTrue(playerManager.contains(dropperPlayer.getUniqueId()));
        dropperPlayer.quitDropper();
        assertFalse(playerManager.contains(dropperPlayer.getUniqueId()));
    }

    @Test
    public void completeMap_ShouldUpdateCurrentMapAndGoToItsSpawn(){
        dropperPlayer.startDropper();
        assertNotEquals(map2SpawnLocation, dropperPlayer.getLocation());
        assertNotEquals(maps.get(1), dropperPlayer.getCurrentMap());
        dropperPlayer.completeMap();
        assertEquals(map2SpawnLocation, dropperPlayer.getLocation());
        assertEquals(maps.get(1), dropperPlayer.getCurrentMap());
    }

    @Test
    public void skipMap_ShouldUpdateCurrentMapAndGoToItsSpawn(){
        dropperPlayer.startDropper();
        assertNotEquals(map2SpawnLocation, dropperPlayer.getLocation());
        assertNotEquals(maps.get(1), dropperPlayer.getCurrentMap());
        dropperPlayer.skipMap();
        assertEquals(map2SpawnLocation, dropperPlayer.getLocation());
        assertEquals(maps.get(1), dropperPlayer.getCurrentMap());
    }

}
