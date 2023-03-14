package io.benlewis.dropin.api;

import io.benlewis.dropin.dropper.game.BasicDropperLobby;

/**
 * Represents a single game. Owns {@link Phase}'s and a {@link Lobby}.
 * @param <T> the player wrapper type for the game
 */
public interface Game<T extends PlayerWrapper> {
    void cancelGame();
    Phase<T> getCurrentPhase();
    BasicDropperLobby getLobby();
}
