package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.MockBukkitTest;
import io.benlewis.dropin.dropper.map.DropperMap;
import io.benlewis.dropin.dropper.rotation.DropperMapRotationFactory;
import io.benlewis.dropin.dropper.rotation.DropperMapRotationType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultDropperPlayerTests extends MockBukkitTest {

    private Player player;

    private List<DropperMap> maps;
    private Location map1SpawnLocation;
    private Location map2SpawnLocation;
    private DropperPlayer dropperPlayer;

    @Override
    @BeforeEach
    public void setUpBukkit() {
        super.setUpBukkit();

        player = server.addPlayer();

        maps = new ArrayList<>();
        World world = server.addSimpleWorld("world");
        map1SpawnLocation = new Location(world, 32, 1, 1);
        map2SpawnLocation = new Location(world, 64, 2, 2);
        DropperMap map1 = mock(DropperMap.class, "map1");
        DropperMap map2 = mock(DropperMap.class, "map2");
        when(map1.getSpawnLocation()).thenReturn(map1SpawnLocation);
        when(map2.getSpawnLocation()).thenReturn(map2SpawnLocation);
        maps.add(map1);
        maps.add(map2);

        DropperMapRotationFactory rotationFactory = new DropperMapRotationFactory(maps, DropperMapRotationType.LINEAR);
        DropperPlayerManager playerManager = new DropperPlayerManager();
        DropperPlayerFactory playerFactory = new DropperPlayerFactory(playerManager, rotationFactory);
        dropperPlayer = playerFactory.create(player);
    }

    @Test
    public void startDropper_ShouldSetCurrentMap(){
        dropperPlayer.startDropper();
        assertEquals(maps.get(0), dropperPlayer.getCurrentMap());
    }

    @Test
    public void startDropper_ShouldTeleportToMapSpawn(){
        assertNotEquals(map1SpawnLocation, dropperPlayer.getPlayer().getLocation(),
                "player and map spawn should be different before player starts game");
        dropperPlayer.startDropper();
        assertEquals(map1SpawnLocation, dropperPlayer.getPlayer().getLocation());
    }

    @Test
    public void quitDropper_ShouldTeleportToPreMapLocation(){
        Location preStartLocation = player.getLocation();
        dropperPlayer.startDropper();
        assertNotEquals(preStartLocation, dropperPlayer.getPlayer().getLocation());
        dropperPlayer.restorePreGameState();
        assertEquals(preStartLocation, dropperPlayer.getPlayer().getLocation());
    }

    @Test
    public void completeMap_ShouldUpdateCurrentMapAndGoToItsSpawn(){
        dropperPlayer.startDropper();
        assertNotEquals(map2SpawnLocation, dropperPlayer.getPlayer().getLocation());
        assertNotEquals(maps.get(1), dropperPlayer.getCurrentMap());
        dropperPlayer.completeMap();
        assertEquals(map2SpawnLocation, dropperPlayer.getPlayer().getLocation());
        assertEquals(maps.get(1), dropperPlayer.getCurrentMap());
    }

    @Test
    public void skipMap_ShouldUpdateCurrentMapAndGoToItsSpawn(){
        dropperPlayer.startDropper();
        assertNotEquals(map2SpawnLocation, dropperPlayer.getPlayer().getLocation());
        assertNotEquals(maps.get(1), dropperPlayer.getCurrentMap());
        dropperPlayer.skipMap();
        assertEquals(map2SpawnLocation, dropperPlayer.getPlayer().getLocation());
        assertEquals(maps.get(1), dropperPlayer.getCurrentMap());
    }

}
