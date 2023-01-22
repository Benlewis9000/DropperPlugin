package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.rotation.MapRotation;
import org.bukkit.entity.Player;

public class DropperPlayerFactory {

    private final DropperPlayerManager playerManager;

    public DropperPlayerFactory(DropperPlayerManager playerManager){
        this.playerManager = playerManager;
    }

    public DropperPlayer create(Player player, MapRotation mapRotation){
        DropperPlayer dropperPlayer = new DefaultDropperPlayer(player, playerManager, mapRotation);
        playerManager.put(player.getUniqueId(), dropperPlayer);
        return dropperPlayer;
    }

}
