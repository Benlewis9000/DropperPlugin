package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.map.DropperMap;
import org.bukkit.entity.Player;

public interface DropperPlayer extends Player {

    /**
     * Join the game.
     */
    void startDropper();

    /**
     * Exit the game and restore player to pre-game state.
     */
    void quitDropper();

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