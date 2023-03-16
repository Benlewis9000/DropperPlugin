package io.benlewis.dropin.dropper.feature;

import io.benlewis.dropin.DropIn;
import io.benlewis.dropin.api.*;
import io.benlewis.dropin.dropper.player.DropperPlayer;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class TeleportToSpawnOnDeath extends DropperFeature implements Listener {

    private final Phase<DropperPlayer> phase;
    private final Lobby<DropperPlayer> lobby;
    private boolean enabled;

    public TeleportToSpawnOnDeath(Phase<DropperPlayer> phase, Lobby<DropperPlayer> lobby, DropIn plugin){
        this.phase = phase;
        this.lobby = lobby;
        this.enabled = false;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event){
        if (!enabled){
            return;
        }
        if (!(event.getEntity() instanceof Player player)){
            return;
        }
        if (!lobby.containsPlayer(player)){
            return;
        }
        if (event.getFinalDamage() < player.getHealth()){
            return;
        }
        event.setCancelled(true);
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null){
            throw new IllegalStateException("Player %s does not have Attribute GENERIC_MAX_HEALTH"
                    .formatted(player.getName()));
        }
        player.setHealth(maxHealth.getDefaultValue());
        DropperPlayer dropperPlayer = lobby.getWrapper(player);
        player.teleport(dropperPlayer.getSpawnpoint());
    }

    @Override
    public Phase<DropperPlayer> getPhase() {
        return phase;
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
