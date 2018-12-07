import daysix.DaySix;
import daysix.Grid;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DaySixTest {

    private ArrayList<String> sampleInput;

    @Before
    public void setUp() throws Exception {
        sampleInput = Lists.newArrayList(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9");
    }

    @Test
    public void parseSampleInput() {
        DaySix daySix = new DaySix();

        Grid grid = daySix.initGrid(sampleInput);

        assertEquals(1, grid.getLeft());
        assertEquals(8, grid.getRight());
        assertEquals(1, grid.getUp());
        assertEquals(9, grid.getDown());
        assertEquals(17, grid.findMaxRepresentedCoordCount());
        assertEquals(16, grid.findContainingRegion(32));

    }

    @Test
    public void parseRealInput() {
        DaySix daySix = new DaySix();

        List<String> realInput = InputLoader.loadInputList("inputDay6.txt");

        Grid grid = daySix.initGrid(realInput);

        // assertEquals(3840, grid.findMaxRepresentedCoordCount());
        assertEquals(46542, grid.findContainingRegion(10000));

    }

}
