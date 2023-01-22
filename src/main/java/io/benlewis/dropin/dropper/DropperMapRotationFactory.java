package io.benlewis.dropin.dropper;

import java.util.Collection;
import java.util.Random;

public class DropperMapRotationFactory {

    public MapRotation create(Collection<DropperMap> maps, DropperMapRotationType rotationType){
        return switch (rotationType) {
            case LINEAR -> createLinearMapRotation(maps);
            case RANDOM -> createRandomMapRotation(maps);
        };
    }

    private MapRotation createRandomMapRotation(Collection<DropperMap> maps){
        return new RandomMapRotation(maps, new Random());
    }

    private MapRotation createLinearMapRotation(Collection<DropperMap> maps){
        return new LinearMapRotation(maps);
    }

}
