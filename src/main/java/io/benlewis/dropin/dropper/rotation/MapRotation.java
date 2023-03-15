package io.benlewis.dropin.dropper.rotation;

import io.benlewis.dropin.dropper.map.DropperMap;
import org.jetbrains.annotations.Nullable;

public interface MapRotation {

    /**
     * Get the next map in the rotation.
     * @return next map
     */
    DropperMap next();

    /**
     * Get the previous map in the rotation, or null if there wasn't one.
     * @return previous map or null
     */
    @Nullable
    DropperMap previous();

}
