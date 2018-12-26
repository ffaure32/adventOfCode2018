package day23;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DayTwentyThree {
    Set<Nanobot> bots;
    public DayTwentyThree(List<String> sample) {
        bots = sample.stream().map(s -> new Nanobot(s)).collect(Collectors.toSet());
    }

    public Nanobot findStrongest() {
        return bots.stream().max(Comparator.comparing(n -> n.radius)).orElseThrow(RuntimeException::new);
    }

    public long countInRange(Nanobot strongest) {
        return bots.stream().filter(b -> b.inRange(strongest)).count();
    }

}
