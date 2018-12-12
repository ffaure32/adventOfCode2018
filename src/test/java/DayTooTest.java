import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DayTooTest {
    DayTwo dayTwo;
    @Before
    public void setUp() {
        dayTwo = new DayTwo();
    }


    @Test
    public void testPartOne() throws Exception {
        Stream<String> lines = InputLoader.loadInputStream("inputDay2.txt");

        AtomicInteger numberOfThrees = new AtomicInteger();
        AtomicInteger numberOfTwos = new AtomicInteger();
        lines.forEach(line -> {
            Map<Character, Long> collectPerLine = line.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            numberOfTwos.addAndGet(collectPerLine.values().contains(2L) ? 1 : 0);
            numberOfThrees.addAndGet(collectPerLine.values().contains(3L) ? 1 : 0);
        });
        System.out.println(numberOfTwos.get() * numberOfThrees.get());
    }

    @Test
    public void testPartTwo() throws Exception {
        dayTwo.partTwo().ifPresent(System.out::println);
    }


    @Test
    public void testFindSiblingsFromExample() {
        List<String> strings = Lists.newArrayList(
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz");
        Optional<String> result = dayTwo.findSiblingsForAllLines(strings);

        assertTrue(result.isPresent());
        assertEquals("fgij", result.get());
    }
    @Test
    public void testRemoveDistinctChars() {
        String s1 = "azerty";
        String s2 = "azdrty";

        String result = dayTwo.removeDistinctChar(s1, s2);

        assertEquals("azrty", result);
        assertTrue(dayTwo.areSiblings(s1, s2));
    }
}
