package io.benlewis.dropin.dropper.feature;

import io.benlewis.dropin.MockBukkitTest;
import io.benlewis.dropin.api.Feature;
import io.benlewis.dropin.api.Lobby;
import io.benlewis.dropin.api.Phase;
import io.benlewis.dropin.dropper.player.DropperPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class TeleportToSpawnOnDeathTests extends MockBukkitTest implements Listener {

    private DropperPlayer dropperPlayer;
    private Feature<DropperPlayer> feature;

    private boolean playerDied = false;

    @BeforeEach
    public void setup(){
        server.getPluginManager().registerEvents(this, plugin);

        World world = server.addSimpleWorld("world");
        Player player = server.addPlayer();
        dropperPlayer = mock(DropperPlayer.class);
        when(dropperPlayer.getPlayer()).thenReturn(player);
        when(dropperPlayer.getSpawnpoint()).thenReturn(new Location(world,2.0,2.0,2.0));

        Phase<DropperPlayer> phase = (Phase<DropperPlayer>) mock(Phase.class);
        Lobby<DropperPlayer> lobby = (Lobby<DropperPlayer>) mock(Lobby.class);
        when(lobby.containsPlayer(player)).thenReturn(true);
        when(lobby.getWrapper(player)).thenReturn(dropperPlayer);

        this.feature = new TeleportToSpawnOnDeath(phase, lobby, plugin);
    }

    @Test
    public void onPlayerDeath_FeatureEnabled_ShouldTeleportPlayerToSpawnpoint(){
        feature.enable();
        Player player = dropperPlayer.getPlayer();

        player.damage(10.0);
        assertNotEquals(2.0, player.getLocation().getX());
        assertEquals(10.0, player.getHealth());

        player.damage(10.0);
        assertEquals(2.0, player.getLocation().getX());
        assertEquals(20.0, player.getHealth());
        assertFalse(playerDied);
    }

    @Test
    public void onPlayerDeath_FeatureDisabled_ShouldDoNothing(){
        feature.disable();
        Player player = dropperPlayer.getPlayer();

        player.damage(10.0);
        assertNotEquals(2.0, player.getLocation().getX());
        assertEquals(10.0, player.getHealth());

        player.damage(10.0);
        assertNotEquals(2.0, player.getLocation().getX());
        assertEquals(0.0, player.getHealth());
        assertTrue(playerDied);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        playerDied = true;
    }

}
