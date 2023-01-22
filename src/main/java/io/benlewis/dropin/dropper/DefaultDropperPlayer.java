package io.benlewis.dropin.dropper;

import io.benlewis.dropin.util.PlayerWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DefaultDropperPlayer extends PlayerWrapper implements DropperPlayer {

    private final DropperPlayerManager playerManager;
    private final Location preDropperLocation;
    private final MapRotation mapRotation;
    private DropperMap currentMap;

    public DefaultDropperPlayer(Player player, DropperPlayerManager playerManager, MapRotation mapRotation) {
        super(player);
        this.playerManager = playerManager;
        this.preDropperLocation = this.getLocation();
        this.mapRotation = mapRotation;
        this.currentMap = null;
    }

    public void startDropper(){
        progressToNextMap();
    }

    public void quitDropper(){
        this.teleport(preDropperLocation);
        playerManager.remove(this.getUniqueId());
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
        this.teleport(currentMap.getSpawnLocation());
    }
}
