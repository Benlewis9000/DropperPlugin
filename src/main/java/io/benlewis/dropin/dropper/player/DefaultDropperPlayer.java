package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.Manager;
import io.benlewis.dropin.dropper.map.DropperMap;
import io.benlewis.dropin.dropper.rotation.MapRotation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DefaultDropperPlayer implements DropperPlayer {

    private final Player player;
    private final Manager<UUID, DropperPlayer> playerManager;
    private final Location preDropperLocation;
    private final MapRotation mapRotation;
    private DropperMap currentMap;

    public DefaultDropperPlayer(Player player, Manager<UUID, DropperPlayer> playerManager, MapRotation mapRotation) {
        this.player = player;
        this.playerManager = playerManager;
        this.preDropperLocation = this.getPlayer().getLocation();
        this.mapRotation = mapRotation;
        this.currentMap = null;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public void startDropper(){
        progressToNextMap();
    }

    public void quitDropper(){
        this.getPlayer().teleport(preDropperLocation);
        playerManager.remove(this.getPlayer().getUniqueId());
    }

    @Override
    public DropperMap getCurrentMap() {
        return currentMap;
    }

    @Override
    public void completeMap() {
        progressToNextMap();
    }

    @Override
    public void skipMap() {
        progressToNextMap();
    }

    private void progressToNextMap(){
        currentMap = mapRotation.next();
        this.getPlayer().teleport(currentMap.getSpawnLocation());
    }
}
