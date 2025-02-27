import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DayTwelveTest {
    private Set<String> sampleInput = Sets.newHashSet(
            "...## => #",
            "..#.. => #",
            ".#... => #",
            ".#.#. => #",
            ".#.## => #",
            ".##.. => #",
            ".#### => #",
            "#.#.# => #",
            "#.### => #",
            "##.#. => #",
            "##.## => #",
            "###.. => #",
            "###.# => #",
            "####. => #"
    );


    @Test
    public void testInitState() {
        String input = "#..#.#..##......###...###";
        DayTwelve day12 = new DayTwelve(20, input, sampleInput);
        assertEquals(325, day12.computeResult());
    }

    @Test
    public void testPartOne() {
        String input = "#.#####.#.#.####.####.#.#...#.......##..##.#.#.#.###..#.....#.####..#.#######.#....####.#....##....#";
        Set<String> realNotes = InputLoader.loadInputStream("inputDay12.txt").collect(Collectors.toSet());
        DayTwelve day12 = new DayTwelve(20, input, realNotes);
        assertEquals(3566, day12.computeResult());
    }


    @Test
    public void testPart2() {
        String input = "#.#####.#.#.####.####.#.#...#.......##..##.#.#.#.###..#.....#.####..#.#######.#....####.#....##....#";
        Set<String> realNotes = InputLoader.loadInputStream("inputDay12.txt").collect(Collectors.toSet());
        DayTwelve day12 = new DayTwelve(50000000000l, input, realNotes);
        assertEquals(1799999999458l, day12.computeResult());
    }

}
