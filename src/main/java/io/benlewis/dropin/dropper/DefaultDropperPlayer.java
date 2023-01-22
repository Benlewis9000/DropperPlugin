package io.benlewis.dropin.dropper;

import io.benlewis.dropin.util.PlayerWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DefaultDropperPlayer extends PlayerWrapper implements DropperPlayer {

    private final Location preDropperLocation;
    private final MapRotation mapRotation;
    private DropperMap currentMap;

    public DefaultDropperPlayer(Player player, MapRotation mapRotation) {
        super(player);
        this.preDropperLocation = this.getLocation();
        this.mapRotation = mapRotation;
        this.currentMap = null;
    }

    public void joinDropper(){
        progressToNextMap();
    }

    public void quitDropper(){
        this.teleport(preDropperLocation);
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
