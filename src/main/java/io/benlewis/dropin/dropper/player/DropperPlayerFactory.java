package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.Manager;
import io.benlewis.dropin.dropper.rotation.DropperMapRotationFactory;
import io.benlewis.dropin.dropper.rotation.MapRotation;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DropperPlayerFactory {

    private final Manager<UUID, DropperPlayer> playerManager;
    private final DropperMapRotationFactory mapRotationFactory;

    public DropperPlayerFactory(Manager<UUID, DropperPlayer> playerManager, DropperMapRotationFactory mapRotationFactory){
        this.playerManager = playerManager;
        this.mapRotationFactory = mapRotationFactory;
    }

    public DropperPlayer create(Player player) throws IllegalArgumentException {
        if (playerManager.contains(player.getUniqueId())){
            throw new IllegalArgumentException("Player with UUID %s is already registered in this factories manager."
                    .formatted(player.getUniqueId()));
        }
        MapRotation mapRotation = mapRotationFactory.create();
        DropperPlayer dropperPlayer = new DefaultDropperPlayer(player, mapRotation);
        playerManager.put(player.getUniqueId(), dropperPlayer);
        return dropperPlayer;
    }

}
