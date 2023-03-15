package io.benlewis.dropin.api;

/**
 * Features are reusable components that define the functionality of a {@link Phase}.
 * @param <T> the player wrapper type that the phase uses
 */
public interface Feature<T extends PlayerWrapper> {
    /**
     * Get the owning {@link Phase} of the Feature.
     * @return the owning phase
     */
    Phase<T> getPhase();

    /**
     * Enable the phase.
     */
    void enable();

    /**
     * Disable the phase.
     */
    void disable();

    /**
     * Query whether the phase is enabled.
     * @return true if the phase is enabled, else false
     */
    boolean isEnabled();
}
