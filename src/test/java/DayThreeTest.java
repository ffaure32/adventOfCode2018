import model.Claim;
import model.ClaimGrid;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        List<String> sample = SAMPLE;

        DayThree dayThree = new DayThree(sample);
        int cells = dayThree.countOverlappingCells();
        assertEquals(4, cells);
    }

    @Test
    public void testPartTwoWithSample() {
        List<String> sample = SAMPLE;

        DayThree dayThree = new DayThree(sample);
        Claim uniqueClaim = dayThree.getUniqueClaim();
        assertEquals(3, uniqueClaim.id);
    }

    @Test
    public void testRealPartOne() throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(INPUT_DAY_3_FILE_NAME).toURI());

        List<String> lines = Files.readAllLines(path);
        DayThree dayThree = new DayThree(lines);
        int cells = dayThree.countOverlappingCells();
        assertEquals(116140, cells);
    }

    @Test
    public void testPartThreeWithSample() throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(INPUT_DAY_3_FILE_NAME).toURI());

        List<String> lines = Files.readAllLines(path);

        DayThree dayThree = new DayThree(lines);
        Claim uniqueClaim = dayThree.getUniqueClaim();
        assertEquals(574, uniqueClaim.id);
    }


    private void fillArray(int[][] array, Claim cl) {
        for (int i = cl.leftEdge; i < cl.leftEdge+cl.width; i++) {
            for (int j = cl.topEdge; j < cl.topEdge+cl.height; j++) {
                array[i][j] += 1;
            }
        }
    }

}
