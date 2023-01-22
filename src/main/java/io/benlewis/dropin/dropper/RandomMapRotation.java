package io.benlewis.dropin.dropper;

import java.util.*;

public class RandomMapRotation extends BasicMapRotation {

    private final DropperMap[] dropperMaps;
    private final Random random;

    public RandomMapRotation(Collection<DropperMap> maps, Random random) {
        super(maps);
        this.dropperMaps = maps.toArray(DropperMap[]::new);
        this.random = random;
    }

    @Override
    public DropperMap next() {
        int index = random.nextInt(dropperMaps.length);
        DropperMap next = dropperMaps[index];
        previousMaps.push(next);
        return next;
    }

}
