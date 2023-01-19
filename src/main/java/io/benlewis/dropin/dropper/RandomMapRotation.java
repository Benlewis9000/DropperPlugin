package io.benlewis.dropin.dropper;

import java.util.*;

public class RandomMapRotation implements MapRotation {

    private final Random random;
    private final DropperMap[] dropperMaps;
    private final Stack<DropperMap> previousMaps;

    public RandomMapRotation(Set<DropperMap> maps, Random random){
        this.dropperMaps = maps.toArray(DropperMap[]::new);
        this.previousMaps = new Stack<>();
        this.random = random;
    }

    public RandomMapRotation(Set<DropperMap> maps, long seed){
        this(maps, new Random(seed));
    }

    public RandomMapRotation(Set<DropperMap> maps){
        this(maps, new Random());
    }

    @Override
    public DropperMap next() {
        int index = random.nextInt(dropperMaps.length);
        DropperMap next = dropperMaps[index];
        previousMaps.push(next);
        return next;
    }

    @Override
    public DropperMap previous() {
        return (previousMaps.size() > 0) ? previousMaps.pop() : null;
    }
}
