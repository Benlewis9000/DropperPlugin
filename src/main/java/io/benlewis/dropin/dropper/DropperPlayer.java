package io.benlewis.dropin.dropper;

public interface DropperPlayer {

    /**
     * Join the game.
     */
    void joinDropper();

    /**
     * Exit the game and restore player to pre-game state.
     */
    void quitDropper();

    /**
     * Complete the current map, progress to the next one.
     */
    void completeMap();

    /**
     * Skip the current map, progress to the next one.
     */
    void skipMap();

}
