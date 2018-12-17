package day17;

import day10.Position;

import java.util.*;
import java.util.stream.Collectors;

import static day17.GroundType.CLAY;
import static day17.GroundType.SAND;

public class GroundMap {
    private final Integer maxY;
    private final Integer minY;
    private final Integer maxX;
    private final Integer minX;
    Map<Integer, Map<Integer, GroundSquare>> map;

    public GroundMap(List<String> lines) {
        map = new HashMap<>();
        for (String line : lines) {
            String[] split = line.split(", ");
            String[] point = split[0].split("=");
            String[] range = split[1].split("=");
            int pointCoord = Integer.valueOf(point[1]);
            String pointType = point[0];
            String[] rangeCoords = range[1].split("\\.\\.");
            for (int i = Integer.valueOf(rangeCoords[0]); i <= Integer.valueOf(rangeCoords[1]); i++) {
                if ("x".equals(pointType)) {
                    addClayToMap(pointCoord, i);
                } else {
                    addClayToMap(i, pointCoord);
                }
            }
        }
        maxY = map.keySet().stream().max(Comparator.comparing(i -> i)).orElseThrow(RuntimeException::new);
        minY = map.keySet().stream().min(Comparator.comparing(i -> i)).orElseThrow(RuntimeException::new);
        Set<Integer> xSet = map.values().stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        maxX = xSet.stream().max(Comparator.comparing(i -> i)).orElseThrow(RuntimeException::new)+1;
        minX = xSet.stream().min(Comparator.comparing(i -> i)).orElseThrow(RuntimeException::new)-1;
        completeGrid();
    }
    private void completeGrid() {
        for (int i = minY; i <= maxY ; i++) {
            Map<Integer, GroundSquare> line = map.computeIfAbsent(i, k -> new HashMap<>());
            for (int j = minX; j <= maxX ; j++) {
                GroundSquare groundSquare = map.get(i).get(j);
                if(groundSquare == null) {
                    groundSquare = new GroundSquare(GroundType.SAND, new Position(j, i));
                    line.put(j, groundSquare);
                }
            }
        }
    }

    public void printGrid() {
        for (int i = minY; i <= maxY ; i++) {
            for (int j = minX; j <= maxX ; j++) {
                map.get(i).get(j).print();
            }
            System.out.println();
        }
    }
    private void addClayToMap(int posX, int posY) {
        Position pos = new Position(posX, posY);
        Map<Integer, GroundSquare> line = map.get(posY);
        if (line == null) {
            line = new HashMap<>();
            map.put(posY, line);
        }
        line.put(posX, new GroundSquare(CLAY, pos));
    }

    Set<GroundSquare> activeSquares = new HashSet<>();
    public void initSpring() {
        Position pos = new Position(500, minY);
        GroundSquare rootSquare = fromMap(pos);
        activeSquares.add(rootSquare);
    }

    public void spreadWater() {
        for(GroundSquare square : new HashSet<>(activeSquares)) {
            if(square.position.y < maxY) {
                GroundSquare down = fromMap(square.position.x, square.position.y + 1);
                boolean filled = fillSquare(down);
                if (filled) {
                    activeSquares.add(down);
                } else {
                    GroundLimit leftLimit = findLeftLimit(square);
                    GroundLimit rightLimit = findRightLimit(square);
                    if (leftLimit.limitType == GroundLimitType.WALL && rightLimit.limitType == GroundLimitType.WALL) {
                        fill(GroundContent.REST, leftLimit.position, rightLimit.position);
                        activeSquares.add(fromMap(square.position.x, square.position.y - 1));
                    } else {
                        fill(GroundContent.FLOW, leftLimit.position, rightLimit.position);
                        if (leftLimit.limitType == GroundLimitType.HOLE) {
                            activeSquares.add(fromMap(leftLimit.position));
                        }
                        if (rightLimit.limitType == GroundLimitType.HOLE) {
                            activeSquares.add(fromMap(rightLimit.position));
                        }
                    }
                }
            }
            activeSquares.remove(square);
        }
    }

    public long countReachableTiles() {
        List<GroundSquare> allTiles = map.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return allTiles.stream().filter(gs -> gs.content != GroundContent.EMPTY).count() +1; //+1 to count spring source
    }

    public long countPersistentTiles() {
        List<GroundSquare> allTiles = map.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return allTiles.stream().filter(gs -> gs.content == GroundContent.REST).count();
    }

    private void fill(GroundContent content, Position left, Position right) {
        for (int i = left.x; i <= right.x; i++) {
            fromMap(i, left.y).content = content;
        }
    }


    private GroundLimit findLeftLimit(GroundSquare square) {
        int initX = square.position.x;
        while(true) {
            GroundSquare current = fromMap(initX, square.position.y);
            GroundSquare down = fromMap(initX, square.position.y+1);
            GroundSquare left = fromMap(initX-1, square.position.y);
            if(down.type == SAND && down.content != GroundContent.REST) {
                return new GroundLimit(current.position, GroundLimitType.HOLE);
            } else if(left == null || left.type == GroundType.CLAY){
                return new GroundLimit(current.position, GroundLimitType.WALL);
            }
            initX--;
        }
    }

    private GroundLimit findRightLimit(GroundSquare square) {
        int initX = square.position.x;
        while(true) {
            GroundSquare current = fromMap(initX, square.position.y);
            GroundSquare right = fromMap(initX+1, square.position.y);
            GroundSquare down = fromMap(initX, square.position.y+1);
            if(down.type == SAND && down.content != GroundContent.REST) {
                return new GroundLimit(current.position, GroundLimitType.HOLE);
            } else if(right == null || right.type == GroundType.CLAY){
                return new GroundLimit(current.position, GroundLimitType.WALL);
            }
            initX++;
        }
    }


    private boolean fillSquare(GroundSquare toFill) {
        if(toFill.type == SAND && toFill.content != GroundContent.REST) {
            toFill.content = GroundContent.FLOW;
            return true;
        }
        return false;
    }

    public GroundSquare fromMap(Position pos) {
        return map.get(pos.y).get(pos.x);
    }

    public GroundSquare fromMap(int x, int y) {
        return map.get(y).get(x);
    }

    public boolean active() {
        return !activeSquares.isEmpty();
    }
}
