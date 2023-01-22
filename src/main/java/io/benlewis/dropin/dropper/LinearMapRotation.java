package io.benlewis.dropin.dropper;

import java.util.Collection;

public class LinearMapRotation extends BasicMapRotation {

    private final DropperMap[] dropperMaps;
    private int currentMapIndex;

    public LinearMapRotation(Collection<DropperMap> maps) {
        super(maps);
        this.dropperMaps = maps.toArray(DropperMap[]::new);
        currentMapIndex = 0;
    }

    @Override
    public DropperMap next() {
        if (currentMapIndex == dropperMaps.length){
            currentMapIndex = 0;
        }
        return dropperMaps[currentMapIndex++];
    }

}
