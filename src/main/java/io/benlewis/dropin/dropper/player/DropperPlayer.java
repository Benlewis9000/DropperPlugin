package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.api.PlayerWrapper;
import io.benlewis.dropin.dropper.map.DropperMap;

public interface DropperPlayer extends PlayerWrapper {
    /**
     * Join the game.
     */
    void startMapRotation();

    /**
     * Restore player to pre-game state.
     */
    void restorePreGameState();

    /**
     * Get the {@link DropperMap} the player is currently on.
     * @return map player is on
     */
    DropperMap getCurrentMap();

    /**
     * Complete the current map, progress to the next one.
     */
    void completeMap();

    /**
     * Skip the current map, progress to the next one.
     */
    void skipMap();
}
