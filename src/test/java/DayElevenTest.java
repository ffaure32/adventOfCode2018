import day11.FuelGrid;
import day11.SquarePosition;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayElevenTest {
    @Test
    public void testFirstSample() {
        FuelGrid grid = new FuelGrid(18);

        SquarePosition powerfulSquare = grid.findPowerfulSquare();

        assertEquals(33, powerfulSquare.y);
        assertEquals(45, powerfulSquare.x);
    }

    @Test
    public void testSecondSample() {
        FuelGrid grid = new FuelGrid(42);

        SquarePosition powerfulSquare = grid.findPowerfulSquare();

        assertEquals(21, powerfulSquare.y);
        assertEquals(61, powerfulSquare.x);
    }

    @Test
    public void testRealInput() {
        FuelGrid grid = new FuelGrid(7672);

        SquarePosition powerfulSquare = grid.findPowerfulSquare();

        assertEquals(22, powerfulSquare.y);
        assertEquals(18, powerfulSquare.x);
    }

    @Test
    @Ignore
    public void testFirstSamplePart2() {
        FuelGrid grid = new FuelGrid(18);

        SquarePosition powerfulSquare = grid.findPowerfulSquareOfAnySize();

        assertEquals(90, powerfulSquare.y);
        assertEquals(269, powerfulSquare.x);
        assertEquals(16, powerfulSquare.size);
    }

    @Test
    public void testRealInputPart2() {
        FuelGrid grid = new FuelGrid(7672);

        SquarePosition powerfulSquare = grid.findPowerfulSquareOfAnySize();

        assertEquals(new SquarePosition(1, 2, 3), powerfulSquare);
    }

}
