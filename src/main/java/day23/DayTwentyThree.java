package day23;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DayTwentyThree {
    Set<Nanobot> bots;
    Map<Nanobot, Long> ranges;
    public DayTwentyThree(List<String> sample) {
        bots = sample.stream().map(s -> new Nanobot(s)).collect(Collectors.toSet());
        ranges = bots.stream().collect(Collectors.toMap(Function.identity(), s->countInRange(s.position)));
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

    private long findMinForCoord(int pos) {
        return (long) bots.stream().map(b -> b.position[pos]).mapToLong(i -> i).min().orElseThrow(RuntimeException::new);
    }

    private long findMaxForCoord(int pos) {
        return (long) bots.stream().map(b -> b.position[pos]).mapToLong(i -> i).max().orElseThrow(RuntimeException::new);
    }

    public long distanceForCenterPoint() {
        Nanobot start= generateStart();
        print(start);
        Queue<Nanobot> priorityQueue = new PriorityQueue<>(10, (n1, n2) -> {
            long n1rangeCount = countInRange(n1);
            long n2rangeCount = countInRange(n2);
            if (n1rangeCount ==n2rangeCount) {
                long n1ToOrigin = n1.distanceToZero;
                long n2ToOrigin = n2.distanceToZero;
                return (int)(n1ToOrigin - n2ToOrigin);
            } else
                return -1 * (int)(n1rangeCount - n2rangeCount);
        });
        priorityQueue.add(start);
        long counter = 0;
        while (!priorityQueue.isEmpty()) {
            Nanobot n = priorityQueue.poll();
            print(n);
            if (n.radius == 0) {
                return n.distanceToZero;
            } else {
                if(counter % 10000 == 0) {
                    System.out.println("pSize:"+priorityQueue.size()+" range for peek "+countInRange(n));
                }
            }
            priorityQueue.addAll(splitNanobot(n));
            counter++;
        }
        return 0l;
    }

    private void print(Nanobot n) {
        System.out.println("Range: " + countInRange(n) + " (" + n.position[0] + "," + n.position[1] + "," + n.position[2] + " r:"+n.radius+") dist: " + n.distanceToZero);
    }

    public Set<Nanobot> splitNanobot(Nanobot src) {
        Set<Nanobot> result = new HashSet<>();
        long newRadius = 0;
        long offset = 1;
        if (src.radius == 1) {
            result.add(new Nanobot(src.position, newRadius));
        } else if (src.radius == 2){
            newRadius = 1;
        } else {
            newRadius = (long) Math.ceil(0.5 * src.radius);
            offset = src.radius - newRadius;
        }
        long finalNewRadius = newRadius;
        Set<Nanobot> newNanos = aroundPositions(src.position, offset).stream().map(pos -> new Nanobot(pos, finalNewRadius)).collect(Collectors.toSet());
        result.addAll(newNanos);
        return result;
    }

    public Set<Long[]> aroundPositions(Long[] initialPosition, long offset) {
        Set<Long[]> newPositions = new HashSet<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if((i != 0 && j != 0 && k != 0)
                    || i == 0 && j == 0 && k == 0) {
                        Long[] newPos = new Long[3];
                        newPos[0] = initialPosition[0] + i * offset;
                        newPos[1] = initialPosition[1] + j * offset;
                        newPos[2] = initialPosition[2] + k * offset;
                        newPositions.add(newPos);
                    }
                }
            }
        }
        return newPositions;
    }

    private void addElements(List<Nanobot> result, Nanobot src, long offset, long newRadius, int pos) {
        Long[] plusOffset = Arrays.copyOf(src.position, 3);
        plusOffset[pos] = src.position[pos]+offset;
        result.add(new Nanobot(plusOffset, newRadius));
        Long[] minOffset = Arrays.copyOf(src.position, 3);
        minOffset[pos] = src.position[pos]-offset;
        result.add(new Nanobot(minOffset, newRadius));
    }

    private Nanobot generateStart() {
        Nanobot result = new Nanobot(findAveragePos(),1);
        boolean containsAll = false;
        while (!containsAll) {
            result.radius *= 2;
            long count = bots.stream().filter(b -> b.inRange(result)).count();
            containsAll = count == 1000l;
        }
        return new Nanobot(result.position, result.radius);
    }



}
