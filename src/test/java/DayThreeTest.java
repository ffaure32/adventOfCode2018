import model.Claim;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayThreeTest {

    public static final ArrayList<String> SAMPLE = Lists.newArrayList("#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2");
    public static final String INPUT_DAY_3_FILE_NAME = "inputDay3.txt";

    @Test
    public void testPattern() {
        Claim claim = Claim.toClaim("#123 @ 3,2: 5x4");
        Assert.assertEquals(123, claim.id);
        Assert.assertEquals(3, claim.leftEdge);
        Assert.assertEquals(2, claim.topEdge);
        Assert.assertEquals(5, claim.width);
        Assert.assertEquals(4, claim.height);
    }

    @Test
    public void testPartOneWithSample() {
        // ARRANGE
        List<String> sample = SAMPLE;
        DayThree dayThree = new DayThree(sample);

        // ACT
        int cells = dayThree.countOverlappingCells();

        // ASSERT
        assertEquals(4, cells);
    }

    @Test
    public void testPartTwoWithSample() {
        // ARRANGE
        List<String> sample = SAMPLE;
        DayThree dayThree = new DayThree(sample);

        // ACT
        Claim uniqueClaim = dayThree.getUniqueClaim();

        // ASSERT
        assertEquals(3, uniqueClaim.id);
    }

    @Test
    public void testRealPartOne() throws Exception {
        // ARRANGE
        List<String> lines = InputLoader.loadInputList(INPUT_DAY_3_FILE_NAME);
        DayThree dayThree = new DayThree(lines);

        // ACT
        int cells = dayThree.countOverlappingCells();

        // ASSERT
        assertEquals(118858, cells);
    }

    @Test
    public void testPartThreeWithSample() throws Exception {
        // ARRANGE
        List<String> lines = InputLoader.loadInputList(INPUT_DAY_3_FILE_NAME);
        DayThree dayThree = new DayThree(lines);

        // ACT
        Claim uniqueClaim = dayThree.getUniqueClaim();

        // ASSERT
        assertEquals(1100, uniqueClaim.id);
    }


    private void fillArray(int[][] array, Claim cl) {
        for (int i = cl.leftEdge; i < cl.leftEdge+cl.width; i++) {
            for (int j = cl.topEdge; j < cl.topEdge+cl.height; j++) {
                array[i][j] += 1;
            }
        }
    }

}
