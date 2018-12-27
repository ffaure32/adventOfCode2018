package day23;

import java.util.*;
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

    public long countInRange(Long[] position) {
        return bots.stream().filter(b -> b.inRange(position)).count();
    }

    public Long[] findAveragePos() {
        Long averages[] = new Long[3];
        findAverageForCoord(averages, 0);
        findAverageForCoord(averages, 1);
        findAverageForCoord(averages, 2);
        return averages;
    }

    public Set<Long[]> aroundPositions(Long[] initialPosition) {
        Set<Long[]> newPositions = new HashSet<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    Long[] newPos = new Long[3];
                    newPos[0] = initialPosition[0]+i;
                    newPos[1] = initialPosition[1]+j;
                    newPos[2] = initialPosition[2]+k;
                    newPositions.add(newPos);
                }
            }
        }
        return newPositions;
    }

    private void findAverageForCoord(Long[] averages, int pos) {
        averages[pos] = (long) bots.stream().map(b -> b.position[pos]).mapToLong(i -> i).average().orElseThrow(RuntimeException::new);

    }

}
