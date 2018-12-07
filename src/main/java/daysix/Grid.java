package daysix;



import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Grid {
    private final List<Coords> initCoords;
    private final Coords upperLeft;
    private final Coords lowerRight;
    private final Coords[][] filledGrid;
    private final Coords[] firstLine;
    private final Coords[] lastLine;
    private final Object[] firstRow;
    private final Object[] lastRow;

    public Grid(List<String> input) {
        AtomicInteger id = new AtomicInteger();
        initCoords = input.stream().map(coords -> buildCoords(id.getAndIncrement(), coords)).collect(Collectors.toList());
        Optional<Coords> minLeft = initCoords.stream().min(Comparator.comparing(c -> c.x));
        int left =minLeft.map(c -> c.x).orElseThrow(RuntimeException::new);
        Optional<Coords> minUp = initCoords.stream().min(Comparator.comparing(c -> c.y));
        int up = minUp.map(c -> c.y).orElseThrow(RuntimeException::new);
        upperLeft = new Coords(id.intValue(), left, up);
        Optional<Coords> maxRight = initCoords.stream().max(Comparator.comparing(c -> c.x));
        int right = maxRight.map(c -> c.x).orElseThrow(RuntimeException::new);
        Optional<Coords> maxDown = initCoords.stream().max(Comparator.comparing(c -> c.y));
        int low = maxDown.map(c -> c.y).orElseThrow(RuntimeException::new);
        lowerRight = new Coords(id.intValue(), right, low);
        filledGrid = new Coords[lowerRight.x+1][lowerRight.y+1];
        firstLine = filledGrid[upperLeft.x];
        lastLine = filledGrid[lowerRight.x];
        firstRow = getColumn(upperLeft.y);
        lastRow = getColumn(lowerRight.y);

        fillGrid();
    }

    public int findMaxRepresentedCoordCount() {
        return initCoords.stream().filter(this::finiteCoords).map(c -> countInGrid(c.id)).max(Integer::compareTo).orElseThrow(RuntimeException::new);
    }

    private Integer countInGrid(int id) {
        int count = 0;
        for (int i = upperLeft.x; i <=lowerRight.x; i++) {
            for (int j = upperLeft.y; j <= lowerRight.y ; j++) {
                if(id == filledGrid[i][j].id) {
                    count++;
                }
            }
        }
        return count;
    }

    private Object[] getColumn(int column) {
        return IntStream.range(upperLeft.x, lowerRight.x)
                .mapToObj(i -> filledGrid[i][column]).toArray();
    }

    private boolean finiteCoords(Coords c) {
        return !(coordsInArray(c, firstLine) || coordsInArray(c, lastLine) || coordsInArray(c, firstRow) || coordsInArray(c, lastRow));
    }

    private boolean coordsInArray(Coords c, Object[] array) {
        return Arrays.stream(array).filter(o -> o!= null).map(o -> (Coords)o).anyMatch(l -> l.id == c.id);
    }

    public void fillGrid() {
        for (int i = upperLeft.x; i <=lowerRight.x; i++) {
            for (int j = upperLeft.y; j <= lowerRight.y ; j++) {
                List<CoordsPath> closestCoord = findClosestCoord(i, j);
                if(closestCoord.size() > 1) {
                    filledGrid[i][j] = new Coords(-1, i, j);
                } else {
                    filledGrid[i][j] = new Coords(closestCoord.get(0).coordId, i, j);
                }
            }

        }
    }

    public int findContainingRegion(int distanceMax) {
        int count = 0;
        for (int i = upperLeft.x; i <=lowerRight.x; i++) {
            for (int j = upperLeft.y; j <= lowerRight.y; j++) {
                int finalI = i;
                int finalJ = j;
                int sum = initCoords.stream().map(c -> diffCoord(c, finalI, finalJ).shortestPath).mapToInt(in -> in).sum();
                if(sum < distanceMax) {
                    count++;
                }
            }
            System.out.println();
        }
        return count;

    }
    private List<CoordsPath> findClosestCoord(int i, int j) {
        Map<Integer, List<CoordsPath>> collect = initCoords.stream().map(c -> diffCoord(c, i, j)).collect(groupingBy(CoordsPath::getShortestPath));
        Optional<Integer> shortestPath = collect.keySet().stream().sorted().findFirst();
        return shortestPath.map(collect::get).orElseThrow(RuntimeException::new);
    }

    private CoordsPath diffCoord(Coords c, int i, int j) {
        return new CoordsPath(c.id, Math.abs(c.x - i)+Math.abs(c.y - j));
    }

    private Coords buildCoords(int id, String coords) {
        String[] coordsArray = coords.split((", "));
        return new Coords(id, Integer.parseInt(coordsArray[0]), Integer.parseInt(coordsArray[1]));
    }

    public int getLeft() {
        return upperLeft.x;
    }

    public int getRight() {
        return lowerRight.x;
    }

    public int getUp() {
        return upperLeft.y;
    }

    public int getDown() {
        return lowerRight.y;
    }

    public void print() {
        for (int i = upperLeft.x; i <=lowerRight.x; i++) {
            for (int j = upperLeft.y; j <= lowerRight.y ; j++) {
                System.out.print(String.format("|%4d",filledGrid[i][j].id));
            }
            System.out.println();
        }
    }
}
