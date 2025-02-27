import day17.GroundMap;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DaySeventeenTest {

    private List<String> sampleLines;

    @Before
    public void setUp() {
        sampleLines = Lists.newArrayList(
                "x=495, y=2..7",
                "y=7, x=495..501",
                "x=501, y=3..7",
                "x=498, y=2..4",
                "x=506, y=1..2",
                "x=498, y=10..13",
                "x=504, y=10..13",
                "y=13, x=498..504");
    }

    @Test
    public void fillGrid() {
        GroundMap map = new GroundMap(sampleLines);
        map.printGrid();
    }

    @Test
    public void startSpring() {
        DaySeventeen day17 = new DaySeventeen();
        GroundMap map = day17.fillGroundMap(sampleLines);
        assertEquals(57, map.countReachableTiles());
        assertEquals(29, map.countPersistentTiles());
    }

    @Test
    public void fillGridRealInput() {
        List<String> lines = InputLoader.loadInputList("inputDay17.txt");
        DaySeventeen day17 = new DaySeventeen();
        GroundMap map = day17.fillGroundMap(lines);

        assertEquals(52800, map.countReachableTiles());
        assertEquals(45210, map.countPersistentTiles());
    }

}
