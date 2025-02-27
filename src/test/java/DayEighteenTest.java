import com.google.common.base.Stopwatch;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import day18.Acre;
import day18.AcreMap;
import day18.AcreType;
import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static day18.AcreType.*;
public class DayEighteenTest {
    @Test
    public void openWithoutChange() {
        List<AcreType> adjacents = Lists.newArrayList();
        Acre acre = new Acre(OPEN, adjacents);

        AcreType type = acre.change();

        assertEquals(OPEN, type);
    }

    @Test
    public void openToTree() {
        ArrayList<AcreType> acreTypes = Lists.newArrayList(TREE, TREE, TREE);
        Acre acre = new Acre(OPEN, acreTypes);

        AcreType type = acre.change();

        assertEquals(TREE, type);
    }

    @Test
    public void treeWithoutChange() {
        List<AcreType> adjacents = Lists.newArrayList();
        Acre acre = new Acre(TREE, adjacents);

        AcreType type = acre.change();

        assertEquals(TREE, type);
    }

    @Test
    public void treeToLumberyard() {
        ArrayList<AcreType> acreTypes = Lists.newArrayList(LUMBERYARD, LUMBERYARD, LUMBERYARD);
        Acre acre = new Acre(TREE, acreTypes);

        AcreType type = acre.change();

        assertEquals(LUMBERYARD, type);
    }

    @Test
    public void lumberyardToOpen() {
        List<AcreType> adjacents = Lists.newArrayList();
        Acre acre = new Acre(LUMBERYARD, adjacents);

        AcreType type = acre.change();

        assertEquals(OPEN, type);
    }

    @Test
    public void lumberyardToOpenDespiteNeighbours() {
        ArrayList<AcreType> acreTypes = Lists.newArrayList(OPEN);
        Acre acre = new Acre(LUMBERYARD, acreTypes);

        AcreType type = acre.change();

        assertEquals(OPEN, type);
    }

    @Test
    public void lumberyardToLumberyard() {
        ArrayList<AcreType> acreTypes = Lists.newArrayList(LUMBERYARD, TREE);
        Acre acre = new Acre(LUMBERYARD, acreTypes);

        AcreType type = acre.change();

        assertEquals(LUMBERYARD, type);
    }


    @Test
    public void changeAcreMap() {
        List<String> lines = Lists.newArrayList(
                ".#.#...|#.",
                ".....#|##|",
                ".|..|...#.",
                "..|#.....#",
                "#.#|||#|#|",
                "...#.||...",
                ".|....|...",
                "||...#|.#|",
                "|.||||..|.",
                "...#.|..|."
        );

        AcreMap map = new AcreMap(lines);
        for (int i = 0; i < 10; i++) {
            map = map.change();
        }
        assertEquals(1147, map.totalResourceValue());

    }

    @Test
    public void changeAcreMapRealInput() {
        List<String> lines = InputLoader.loadInputList("inputDay18.txt");

        AcreMap map = new AcreMap(lines);
        for (int i = 0; i < 10; i++) {
            map = map.change();
        }
        assertEquals(582494, map.totalResourceValue());
    }

    @Test
    public void changeAcreMapRealInputPart2() {
        List<String> lines = InputLoader.loadInputList("inputDay18.txt");

        AcreMap map = new AcreMap(lines);

        int previousIndex = -1;
        int newIndex = -1;
        BiMap<AcreMap, Integer> olderMaps = HashBiMap.create();

        int loopsize = 1000000000;
        for (int i = 0; i < loopsize; i++) {
            map = map.change();
            if(olderMaps.containsKey(map)) {
                previousIndex = olderMaps.get(map);
                newIndex = i;
                break;
            } else {
                olderMaps.put(map, i);
            }

        }
        int spread = newIndex - previousIndex;
        int result = (loopsize - previousIndex) % spread;
        long value = olderMaps.inverse().get(previousIndex+result-1).totalResourceValue();

        assertEquals(174584, value);
    }

    private List<Acre> buildAcres(ArrayList<AcreType> acreTypes) {
        return acreTypes.stream().map(t -> new Acre(t, Lists.newArrayList())).collect(Collectors.toList());
    }

}
