package io.benlewis.dropin.dropper.rotation;

import io.benlewis.dropin.dropper.map.DropperMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomMapRotationTests {

    @Test
    public void previous_ShouldGetPreviousMapOrNull(){
        DropperMap map1 = Mockito.mock(DropperMap.class);
        DropperMap map2 = Mockito.mock(DropperMap.class);
        Set<DropperMap> maps = new HashSet<>();
        maps.add(map1);
        maps.add(map2);
        MapRotation randomMapRotation = new RandomMapRotation(maps, new Random());
        MapRotationTests.previous_ShouldGetPreviousMapOrNull(randomMapRotation);
    }

    @Test
    public void next_ShouldGetMap2ThenMap1(){
        DropperMap map1 = Mockito.mock(DropperMap.class);
        DropperMap map2 = Mockito.mock(DropperMap.class);
        Set<DropperMap> maps = new HashSet<>();
        maps.add(map1);
        maps.add(map2);
        // Seed of one outputs 1, 0 when bounds 0-2
        // thus this seed will select map2 then map1
        Random random = new Random(1);
        MapRotation randomMapRotation = new RandomMapRotation(maps, random);
        assertEquals(map2, randomMapRotation.next());
        assertEquals(map1, randomMapRotation.next());
    }

}
