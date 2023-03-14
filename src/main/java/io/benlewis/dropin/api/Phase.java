package io.benlewis.dropin.api;

import org.bukkit.entity.Player;

/**
 * Phases make up the different sections of a {@link Game}.
 * @param <T> the player wrapper type for the game
 */
public interface Phase<T extends PlayerWrapper> {

    /**
     * Get the owning {@link Game} for the Phase.
     * @return the owning game
     */
    Game<T> getGame();

    /**
     * Begin a phase.
     */
    void beginPhase();

    /**
     * Complete a phase.
     */
    void completePhase();

    /**
     * Cancel a phase.
     */
    void cancelPhase();

    /**
     * Called when a {@link Player} is succesfully added to the parent {@link Game}.
     * @param player game wrapper of the added player
     */
    void onPlayerAdded(T player);

    /**
     * Called when a {@link Player} is succesfully removed from the  {@link Game}.
     * @param player game wrapper of the added player
     */
    void onPlayerRemoved(T player);
}
