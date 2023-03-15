package io.benlewis.dropin.dropper.player;

import io.benlewis.dropin.dropper.BasicManager;
import io.benlewis.dropin.dropper.Manager;

import java.util.UUID;

public class DropperPlayerManager implements Manager<UUID, DropperPlayer> {

    private final BasicManager<UUID, DropperPlayer> players;

    public DropperPlayerManager(){
        this.players = new BasicManager<>();
    }

    @Override
    public void put(UUID id, DropperPlayer value) {
        players.put(id, value);
    }

    @Override
    public boolean contains(UUID id) {
        return players.contains(id);
    }

    @Override
    public DropperPlayer get(UUID id) {
        return players.get(id);
    }

    @Override
    public void remove(UUID id) {
        if (this.contains(id)){
            this.get(id).restorePreGameState();
        }
        players.remove(id);
    }
}
