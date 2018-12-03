import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DayThreeTest {
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
    public void testWithSample() {
        List<String> sample = Lists.newArrayList("#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2");
        List<Claim> claims = sample.stream().map(cl -> Claim.toClaim(cl)).collect(Collectors.toList());
        Optional<Claim> maxWidth = claims.stream().max(Comparator.comparing(cl -> cl.totalWidth()));
        Optional<Claim> maxHeight = claims.stream().max(Comparator.comparing(cl -> cl.totalHeight()));
        int width =  maxWidth.get().totalWidth();
        int height = maxHeight.get().totalHeight();

        int array[][] = new int[width][height];

        claims.stream().forEach(cl -> fillArray(array, cl));
        int cells = 0;
        for (int[] line : array) {
            cells += Arrays.stream(line).filter(i -> i>1).count();
        }
        assertEquals(4, cells);
    }

    @Test
    public void realTest() throws Exception {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("inputDay3.txt").toURI());

        List<String> lines = Files.readAllLines(path);
        List<Claim> claims = lines.stream().map(cl -> Claim.toClaim(cl)).collect(Collectors.toList());
        Optional<Claim> maxWidth = claims.stream().max(Comparator.comparing(cl -> cl.totalWidth()));
        Optional<Claim> maxHeight = claims.stream().max(Comparator.comparing(cl -> cl.totalHeight()));
        int width =  maxWidth.get().totalWidth();
        int height = maxHeight.get().totalHeight();

        int array[][] = new int[width][height];

        claims.stream().forEach(cl -> fillArray(array, cl));
        int cells = 0;
        for (int[] line : array) {
            cells += Arrays.stream(line).filter(i -> i>1).count();
        }
        assertEquals(116140, cells);
    }

    private void fillArray(int[][] array, Claim cl) {
        for (int i = cl.leftEdge; i < cl.leftEdge+cl.width; i++) {
            for (int j = cl.topEdge; j < cl.topEdge+cl.height; j++) {
                array[i][j] += 1;
            }
        }
    }

}
