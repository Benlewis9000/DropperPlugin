package io.benlewis.dropin.dropper.rotation;

import io.benlewis.dropin.dropper.map.DropperMap;

import java.util.Collection;
import java.util.Random;

public class DropperMapRotationFactory {

    private final Collection<DropperMap> maps;
    private final DropperMapRotationType rotationType;

    public DropperMapRotationFactory(Collection<DropperMap> maps, DropperMapRotationType rotationType){
        this.maps = maps;
        this.rotationType = rotationType;
    }

    public MapRotation create(){
        return switch (rotationType) {
            case LINEAR -> new LinearMapRotation(maps);
            case RANDOM -> new RandomMapRotation(maps, new Random());
        };
    }

}
