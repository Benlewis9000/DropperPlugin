package io.benlewis.dropin.api;

import org.bukkit.entity.Player;

public interface PlayerWrapper {
    /**
     * Get the {@link Player} instance this object wraps.
     * @return the wrapped player
     */
    Player getPlayer();
}
