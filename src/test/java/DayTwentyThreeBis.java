import day23.Nanobot;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DayTwentyThreeBis {
    Set<Nanobot> bots;
    Map<Nanobot, Long> ranges;
    public DayTwentyThreeBis(List<String> sample) {
        bots = sample.stream().map(s -> new Nanobot(s)).collect(Collectors.toSet());
        ranges = bots.stream().collect(Collectors.toMap(Function.identity(), s -> countInRange(s.position)));
    }

    public void execute() {
        // Boring stuff reading input
        Nanobot startOctohedron = generateStartOcothedron();
        Queue<Nanobot> pQ = new PriorityQueue<>(10, new Comparator<Nanobot>() {
            public int compare(Nanobot x, Nanobot y) {
                if (countInRange(x).equals(countInRange(y))) {
                    return x.distanceToZero.compareTo(y.distanceToZero);
                } else {
                    return -1 * countInRange(x).compareTo(countInRange(y));
                }
            };
        });
        pQ.add(startOctohedron);
        while (!pQ.isEmpty()) {
            Nanobot n = pQ.poll();
            if (n.radius == 0) {
                System.out.println("Found: " + countInRange(n) + " (" + n.position[0] + "," + n.position[1] + "," + n.position[2] + ") dist: " + n.distanceToZero);
                System.exit(1);
            }
            pQ.addAll(splitNanobot(n));
        }
    }

    public Long countInRange(Nanobot strongest) {
        return bots.stream().filter(b -> b.inRange(strongest)).count();
    }

    public long countInRange(Long[] position) {
        return bots.stream().filter(b -> b.inRange(position)).count();
    }



    public List<Nanobot> splitNanobot(Nanobot src) {
        List<Nanobot> result = new ArrayList<>();
        long newR = 0;
        long offset = 1;
        if (src.radius == 1) {
            result.add(new Nanobot(src.position[0], src.position[1], src.position[2], newR));
        } else if (src.radius == 2){
            newR = 1;
        } else {
            newR = (long) Math.ceil(0.556 * src.radius);
            offset = src.radius - newR;
        }
        result.add(new Nanobot(src.position[0] - offset, src.position[1], src.position[2], newR));
        result.add(new Nanobot(src.position[0] + offset, src.position[1], src.position[2], newR));
        result.add(new Nanobot(src.position[0], src.position[1] + offset, src.position[2], newR));
        result.add(new Nanobot(src.position[0], src.position[1] - offset, src.position[2], newR));
        result.add(new Nanobot(src.position[0], src.position[1], src.position[2] + offset, newR));
        result.add(new Nanobot(src.position[0], src.position[1], src.position[2] - offset, newR));
        return result;
    }

    public Nanobot generateStartOcothedron() {
        Nanobot result = new Nanobot(findAveragePos(),1);
        boolean containsAll = false;
        while (!containsAll) {
            result.radius *= 2;
            long count = bots.stream().filter(b -> b.inRange(result)).count();
            containsAll = count == 1000l;
        }
        return new Nanobot(result.position, result.radius);
    }


    public Long[] findAveragePos() {
        Long averages[] = new Long[3];
        findMathAverageForCoord(averages, 0);
        findMathAverageForCoord(averages, 1);
        findMathAverageForCoord(averages, 2);
        return averages;
    }

    private void findMathAverageForCoord(Long[] averages, int i) {
        long lowestX = findMinForCoord(i);
        long highestX = findMaxForCoord(i);
        averages[i] = lowestX + (highestX-lowestX)/2;
    }

    private long findMinForCoord(int pos) {
        return (long) bots.stream().map(b -> b.position[pos]).mapToLong(i -> i).min().orElseThrow(RuntimeException::new);
    }

    private long findMaxForCoord(int pos) {
        return (long) bots.stream().map(b -> b.position[pos]).mapToLong(i -> i).max().orElseThrow(RuntimeException::new);
    }

}
