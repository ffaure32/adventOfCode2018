import day10.Position;
import day13.PathType;
import day13.TrackMap;
import day13.TrackPath;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DayThirteenTest {

    @Test
    public void manageChar() {
        char input = '-';
        TrackPath path = new TrackPath(0, 0, input);

        assertNull(path.getCart());
        assertEquals(0,path.getX());
        assertEquals(0,path.getY());
        assertEquals(PathType.HORIZONTAL, path.pathType);
    }

    @Test
    public void basicSampleData() {
        ArrayList<String> input = Lists.newArrayList("|", "v", "|", "|", "|", "^", "|");

        TrackMap trackMap = new TrackMap(input);

        Map<Integer, List<TrackPath>> map = trackMap.map;
        assertEquals(7, map.size());
        assertTrue(map.values().stream().allMatch(tp -> tp.size() == 1));
        map.values().stream().forEach(tpl -> {
            assertEquals(1, tpl.size());
            assertTrue(tpl.stream().allMatch(tp -> tp.pathType == PathType.VERTICAL));
        });
        assertEquals(2, trackMap.carts.size());
    }

    @Test
    public void oneMoveSampleData() {
        ArrayList<String> input = Lists.newArrayList("|", "v", "|", "|", "|", "^", "|");

        TrackMap trackMap = new TrackMap(input);

        trackMap.move();
        Map<Integer, List<TrackPath>> map = trackMap.map;
        assertEquals(7, map.size());
        assertTrue(map.values().stream().allMatch(tp -> tp.size() == 1));
        map.values().stream().forEach(tpl -> {
            assertEquals(1, tpl.size());
            assertTrue(tpl.stream().allMatch(tp -> tp.pathType == PathType.VERTICAL));
        });
        assertEquals(2, trackMap.carts.size());
    }

    @Test
    public void findCollisionSampleData() {
        ArrayList<String> input = Lists.newArrayList("|", "v", "|", "|", "|", "^", "|");

        TrackMap trackMap = new TrackMap(input);

        Position position = null;
        do {
            trackMap.move();
            position = trackMap.firstCollision;
        } while(position == null);
        assertEquals(new Position(0, 3), position);
    }

List<String> inputSample = Lists.newArrayList(
      "/->-\\        ",
                "|   |  /----\\",
                "| /-+--+-\\  |",
                "| | |  | v  |",
                "\\-+-/  \\-+--/",
                "  \\------/   ");

    @Test
    public void findCollisionComplexSampleData() {

        TrackMap trackMap = new TrackMap(inputSample);

        Position position = null;
        do {
            trackMap.move();
            position = trackMap.firstCollision;
        } while(position == null);
        assertEquals(new Position(7, 3), position);
    }

    @Test
    public void findCollisionRealInput() {
    List<String> input = InputLoader.loadInputList("inputDay13.txt");
        TrackMap trackMap = new TrackMap(input);

        Position position = null;
        do {
            trackMap.move();
            position = trackMap.firstCollision;
        } while(position == null);
        assertEquals(new Position(64, 57), position);
    }


    @Test
    public void removeCollisionsComplexSampleData() {
        List<String> inputSample = Lists.newArrayList(
                "/>-<\\  ",
                "|   |  ",
                "| /<+-\\",
                "| | | v",
                "\\>+</ |",
                "  |   ^",
                "  \\<->/");

        TrackMap trackMap = new TrackMap(inputSample);

        int remainingCards;
        do {
            trackMap.move();
            remainingCards = trackMap.carts.size();
        } while(remainingCards > 1);

        assertEquals(new Position(6, 4), trackMap.carts.get(0).position);
    }

    @Test
    public void removeCollisionsRealInput() {
        List<String> input = InputLoader.loadInputList("inputDay13.txt");
        TrackMap trackMap = new TrackMap(input);

        int remainingCards;
        do {
            trackMap.move();
            remainingCards = trackMap.carts.size();
        } while(remainingCards > 1);

        assertEquals(new Position(136, 8), trackMap.carts.get(0).position);
    }
}
