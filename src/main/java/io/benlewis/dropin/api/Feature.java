package io.benlewis.dropin.api;

/**
 * Features are reusable components that define the functionality of a {@link Phase}.
 * @param <T> the player wrapper type that the phase uses
 */
public interface Feature<T extends PlayerWrapper> {
    Phase<T> getPhase();
    void enable();
    void disable();
}
