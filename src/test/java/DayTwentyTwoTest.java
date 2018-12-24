import day10.Position;
import day22.Cave;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayTwentyTwoTest {
    @Test
    public void warmup() {
        Cave cave = new Cave(510, new Position(10, 10));

        assertEquals(0, cave.geologicalIndex(0, 0));
        assertEquals(0, cave.geologicalIndex(10, 10));
        assertEquals(4*16807, cave.geologicalIndex(4, 0));
    }

    @Test
    public void erosionLevels() {
        Cave cave = new Cave(510, new Position(10, 10));

        assertEquals(510, cave.erosionLevel(new Position(0, 0)));
        assertEquals(17317, cave.erosionLevel(new Position(1, 0)));
        assertEquals(8415, cave.erosionLevel(new Position(0, 1)));
        assertEquals(1805, cave.erosionLevel(new Position(1, 1)));
        assertEquals(510, cave.erosionLevel(new Position(10, 10)));
    }

    @Test
    public void riskLevel() {
        Cave cave = new Cave(510, new Position(10, 10));

        assertEquals(114, cave.riskLevel());
    }

    @Test
    public void riskLevelRealInput() {
        Cave cave = new Cave(8112, new Position(13, 743));

        assertEquals(10395, cave.riskLevel());
    }

    @Test
    public void print() {
        Cave cave = new Cave(510, new Position(10, 10));
        cave.print();
    }

}
