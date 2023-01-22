package io.benlewis.dropin.dropper.rotation;

import io.benlewis.dropin.dropper.map.DropperMap;
import io.benlewis.dropin.dropper.rotation.MapRotation;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapRotationTests {

    public static void previous_ShouldGetPreviousMapOrNull(MapRotation mapRotation){
        Stack<DropperMap> mapsStack = new Stack<>();
        final int TEST_DEPTH = 100;
        for (int i = 0; i < TEST_DEPTH; i++){
            mapsStack.push(mapRotation.next());
        }
        // Unwind and compare
        for (int i = 0; i < TEST_DEPTH; i++){
            assertEquals(mapsStack.pop(), mapRotation.previous());
        }
        assertNull(mapRotation.previous());
    }

}
