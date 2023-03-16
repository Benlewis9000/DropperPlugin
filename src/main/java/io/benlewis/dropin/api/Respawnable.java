package io.benlewis.dropin.api;

import org.bukkit.Location;

public interface Respawnable {
    /**
     * Get the location where the entity should next respawn.
     * @return location to respawn
     */
    Location getSpawnpoint();
}
