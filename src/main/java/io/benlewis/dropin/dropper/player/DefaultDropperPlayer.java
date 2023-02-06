package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.map.DropperMap;
import io.benlewis.dropin.dropper.rotation.MapRotation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DefaultDropperPlayer implements DropperPlayer {

    private final Player player;
    private final Location preDropperLocation;
    private final MapRotation mapRotation;
    private DropperMap currentMap;

    public DefaultDropperPlayer(Player player, MapRotation mapRotation) {
        this.player = player;
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

    public void restorePreGameState(){
        this.getPlayer().teleport(preDropperLocation);
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
