package dropper;

import io.benlewis.dropin.dropper.DropperMap;
import io.benlewis.dropin.dropper.MapRotation;
import io.benlewis.dropin.dropper.RandomMapRotation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class RandomMapRotationTests {

    @Test
    public void previous_ShouldGetPreviousMapOrNull(){
        DropperMap map1 = Mockito.mock(DropperMap.class);
        DropperMap map2 = Mockito.mock(DropperMap.class);
        Set<DropperMap> maps = new HashSet<>();
        maps.add(map1);
        maps.add(map2);
        MapRotation randomMapRotation = new RandomMapRotation(maps);
        MapRotationTests.previous_ShouldGetPreviousMapOrNull(randomMapRotation);
    }

}
