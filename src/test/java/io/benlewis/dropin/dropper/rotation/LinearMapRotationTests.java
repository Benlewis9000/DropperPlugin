package io.benlewis.dropin.dropper.rotation;

import io.benlewis.dropin.dropper.map.DropperMap;
import io.benlewis.dropin.dropper.rotation.LinearMapRotation;
import io.benlewis.dropin.dropper.rotation.MapRotation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearMapRotationTests {

    @Test
    public void next_ShouldTraverseMapsInOrderAndLoop(){
        DropperMap map1 = Mockito.mock(DropperMap.class);
        DropperMap map2 = Mockito.mock(DropperMap.class);
        List<DropperMap> maps = new ArrayList<>();
        maps.add(map1);
        maps.add(map2);
        MapRotation linearMapRotation = new LinearMapRotation(maps);
        assertEquals(map1, linearMapRotation.next());
        assertEquals(map2, linearMapRotation.next());
        assertEquals(map1, linearMapRotation.next());
    }

}
