import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class DayOneTest {
    @Test
    public void dayOnePartOne() throws Exception {
        Stream<String> stream = InputLoader.loadInputStream("inputDay1.txt");
        int sum = stream.map(in -> Integer.parseInt(in)).mapToInt(Integer::intValue).sum();
        assertEquals(518, sum);
    }

    @Test
    public void dayOnePartTwo() throws Exception {
        List<String> lines = InputLoader.loadInputList("inputDay1.txt");

        AtomicInteger result = new AtomicInteger();
        Set<Integer> duplicates = new HashSet<>();

        Optional<Integer> first = Optional.empty();
        while(!first.isPresent()) {
            first = lines.stream().map(Integer::parseInt).map(in -> result.addAndGet(in)).collect(Collectors.toList()).stream().filter(in -> !duplicates.add(in)).findFirst();
        }
        assertEquals(72889, first.get().intValue());
    }

}