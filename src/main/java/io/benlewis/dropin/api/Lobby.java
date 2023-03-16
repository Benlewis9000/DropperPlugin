package io.benlewis.dropin.api;

import org.bukkit.entity.Player;

/**
 * Represents the collection of players in a game. Handles adding and removing.
 * @param <T> the player wrapper type the game uses
 */
public interface Lobby<T extends PlayerWrapper> {
    /**
     * Add a {@link Player} to the lobby.
     * @param player to add
     * @throws IllegalArgumentException if the given Player could not be added
     */
    T addPlayer(Player player) throws IllegalArgumentException;

    /**
     * Remove a {@link Player} from the lobby.
     * @param player to remove
     * @throws IllegalArgumentException if the given Player could not be removed
     */
    void removePlayer(Player player) throws IllegalArgumentException;

    /**
     * Get the {@link PlayerWrapper} for a {@link Player} in the Lobby.
     * @param player to get the wrapper of
     * @return the players wrapper
     * @throws IllegalArgumentException if the specifiec Player is not in the Lobby.
     */
    T getWrapper(Player player) throws IllegalArgumentException;

    /**
     * Query whether a {@link Player} is in the Lobby.
     * @param player to check is in lobby
     * @return true if player is in the lobby, else false
     */
    boolean containsPlayer(Player player);
}
