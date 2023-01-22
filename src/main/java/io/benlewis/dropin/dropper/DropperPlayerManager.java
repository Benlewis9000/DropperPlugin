package io.benlewis.dropin.dropper;

import java.util.HashMap;
import java.util.UUID;

public class DropperPlayerManager implements Manager<UUID, DropperPlayer> {

    private final HashMap<UUID, DropperPlayer> players;

    public DropperPlayerManager(){
        this.players = new HashMap<>();
    }

    @Override
    public void put(UUID id, DropperPlayer value) {
        if (contains(id)){
            throw new IllegalArgumentException("Player with UUID " + id + " is already registered in this manager.");
        }
        players.put(id, value);
    }

    @Override
    public boolean contains(UUID id) {
        return players.containsKey(id);
    }

    @Override
    public DropperPlayer get(UUID id) {
        return players.get(id);
    }

    @Override
    public void remove(UUID id) {
        players.remove(id);
    }
}
