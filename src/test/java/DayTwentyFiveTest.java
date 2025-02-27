import day25.DayTwentyFive;
import day25.Star;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayTwentyFiveTest {
    @Test
    public void initModel() {
        DayTwentyFive d25 = new DayTwentyFive();
        d25.addStar(new Star(0, 0, 0, 0));
        d25.addStar(new Star(3, 0, 0, 0));
        d25.addStar(new Star(0, 3, 0, 0));
        d25.addStar(new Star(0, 0, 3, 0));
        d25.addStar(new Star(0, 0, 0, 3));
        d25.addStar(new Star(0, 0, 0, 6));
        d25.addStar(new Star(9, 0, 0, 0));
        d25.addStar(new Star(12, 0, 0, 0));

        assertEquals(2, d25.constellations.size());
    }

    @Test
    public void sample2() {
        DayTwentyFive d25 = new DayTwentyFive();
        d25.addStar(new Star(-1, 2, 2, 0));
        d25.addStar(new Star(0, 0, 2, -2));
        d25.addStar(new Star(0, 0, 0, -2));
        d25.addStar(new Star(-1, 2, 0, 0));
        d25.addStar(new Star(-2, -2, -2, 2));
        d25.addStar(new Star(3, 0, 2, -1));
        d25.addStar(new Star(-1, 3, 2, 2));
        d25.addStar(new Star(-1, 0, -1, 0));
        d25.addStar(new Star(0, 2, 1, -2));
        d25.addStar(new Star(3, 0, 0, 0));
        assertEquals(4, d25.constellations.size());

    }

    @Test
    public void sample3() {
        DayTwentyFive d25 = new DayTwentyFive();
        d25.addStar(new Star(1, -1, 0, 1));
        d25.addStar(new Star(2, 0, -1, 0));
        d25.addStar(new Star(3, 2, -1, 0));
        d25.addStar(new Star(0, 0, 3, 1));
        d25.addStar(new Star(0, 0, -1, -1));
        d25.addStar(new Star(2, 3, -2, 0));
        d25.addStar(new Star(-2, 2, 0, 0));
        d25.addStar(new Star(2, -2, 0, -1));
        d25.addStar(new Star(1, -1, 0, -1));
        d25.addStar(new Star(3, 2, 0, 2));
        assertEquals(3, d25.constellations.size());
    }

    @Test
    public void sample4() {
        DayTwentyFive d25 = new DayTwentyFive();
        d25.addStar(new Star(1,-1,-1,-2));
        d25.addStar(new Star(-2,-2,0,1));
        d25.addStar(new Star(0,2,1,3));
        d25.addStar(new Star(-2,3,-2,1));
        d25.addStar(new Star(0,2,3,-2));
        d25.addStar(new Star(-1,-1,1,-2));
        d25.addStar(new Star(0,-2,-1,0));
        d25.addStar(new Star(-2,2,3,-1));
        d25.addStar(new Star(1,2,2,0));
        d25.addStar(new Star(-1,-2,0,-2));
        assertEquals(8, d25.constellations.size());
    }

    @Test
    public void testRealInput() {
        DayTwentyFive d25 = new DayTwentyFive();

        List<String> lines = InputLoader.loadInputList("inputDay25.txt");
        for(String line : lines) {
            String[] split = line.split(",");
            d25.addStar(new Star(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
        }
        assertEquals(377, d25.constellations.size());
    }

}