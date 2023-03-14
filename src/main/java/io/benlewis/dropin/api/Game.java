package io.benlewis.dropin.api;

/**
 * Represents a single game. Owns {@link Phase}'s and a {@link Lobby}.
 * @param <T> the player wrapper type for the game
 */
public interface Game<T extends PlayerWrapper> {
    /**
     * Cancel a game.
     */
    void cancelGame();

    /**
     * Get the {@link Phase} that the Game is currently in.
     * @return the active phase
     */
    Phase<T> getActivePhase();

    /**
     * Set the active {@link Phase} for the Game.
     * @param phase to set as active
     */
    void setActivePhase(Phase<T> phase);

    /**
     * Get the {@link Lobby} for the game.
     * @return the games lobby
     */
    Lobby<T> getLobby();
}
