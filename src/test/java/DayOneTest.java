import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOneTest {
    @Test
    public void dayOnePartOne() throws Exception {
        Stream<String> stream = InputLoader.loadInputStream("inputDay1.txt");
        int sum = stream.map(in -> Integer.parseInt(in)).peek(System.out::println).mapToInt(Integer::intValue).sum();
        System.out.println(sum);
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
        System.out.println(first.get());
    }

}