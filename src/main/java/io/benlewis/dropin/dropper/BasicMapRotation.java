package io.benlewis.dropin.dropper;

import java.util.*;

public abstract class BasicMapRotation implements MapRotation {

    protected final Stack<DropperMap> previousMaps;

    public BasicMapRotation(Collection<DropperMap> maps){
        if (maps.isEmpty()) {
            throw new IllegalArgumentException("The collection of maps in a rotation may not be empty.");
        }
        this.previousMaps = new Stack<>();
    }

    @Override
    public DropperMap previous() {
        return (previousMaps.size() > 0) ? previousMaps.pop() : null;
    }

}
