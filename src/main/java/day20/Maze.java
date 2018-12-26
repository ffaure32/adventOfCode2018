package day20;

import day10.Position;

import java.util.*;

public class Maze {

    public static final char OPENING = '(';
    public static final char CLOSING = ')';
    public static final char SEPARATOR = '|';
    public Set<Position> farRooms = new HashSet<>();
    private int farRoomsLimit = 1000;

    public int findFurthestRoom() {
        Set<Position> usedDoors = new HashSet<>();
        Position start = new Position(0, 0);
        Set<Position> firstPathes = findNextPathes(start, usedDoors);
        int doorsCount = 0;
        while(!firstPathes.isEmpty()) {
            Set<Position> newPathes = new HashSet<>();
            for(Position path : firstPathes) {
                newPathes.addAll(findNextPathes(path, usedDoors));
            }
            firstPathes = newPathes;
            if(doorsCount>= farRoomsLimit-3) {
                farRooms.addAll(newPathes);
            }
            doorsCount++;
        }
        return doorsCount;
    }

    private Set<Position> findNextPathes(Position position, Set<Position> usedDoors) {
        Set<Position> path = new HashSet<>();
        Position up = position.up();
        if(isDoor(maze.get(up))
            && !usedDoors.contains(up)) {
            usedDoors.add(up);
            path.add(up.up());
        }
        Position down = position.down();
        if(isDoor(maze.get(down))
                && !usedDoors.contains(down)) {
            usedDoors.add(down);
            path.add(down.down());
        }
        Position left = position.left();
        if(isDoor(maze.get(left))
                && !usedDoors.contains(left)) {
            usedDoors.add(left);
            path.add(left.left());
        }
        Position right = position.right();
        if(isDoor(maze.get(right))
                && !usedDoors.contains(right)) {
            usedDoors.add(right);
            path.add(right.right());
        }
        return path;

    }

    public static boolean isDoor(Route route) {
        return route != null && (route == Route.HDOOR || route == Route.VDOOR);
    }
    public enum Route {
        START('X'),
        WALL('#'),
        PATH('.'),
        HDOOR('-'),
        VDOOR('|');

        public char sign;

        Route(char sign) {
            this.sign = sign;
        }

    }

    public enum Direction {
        WEST('W'), EAST('E'), NORTH('N'), SOUTH('S');

        public char sign;

        Direction(char sign) {
            this.sign = sign;
        }

        public static Direction from(char sign) {
            return Arrays.stream(Direction.values())
                    .filter(d -> d.sign == sign).findFirst().orElseThrow(RuntimeException::new);
        }
    }

    public Map<Position, Route> maze = new HashMap<>();

    public Maze(String input) {
        drawMazeDirectly(new Position(0,0), input.substring(1, input.length() - 1));
    }

    public void drawMazeDirectly(Position position, String input) {
        int firstParenthesis = input.indexOf(OPENING);

        if (firstParenthesis >= 0) {
            Position newPosition = drawMazePart(position, input.substring(0, firstParenthesis));
            int close = findClosing(input, firstParenthesis);
            String parenthesisContent = input.substring(firstParenthesis + 1, close);
            List<Integer> separator = Maze.findSeparator(parenthesisContent);
            Set<String> parts = buildParts(parenthesisContent, separator);
            if(parts.stream().anyMatch(s -> s.isEmpty())) {
                String newinput = input.substring(close + 1);
                drawMazeDirectly(newPosition, newinput);
            } else {
                for (String part : parts) {
                    String newinput = part + input.substring(close + 1);
                    drawMazeDirectly(newPosition, newinput);
                }
            }
        } else {
            drawMazePart(position, input);
        }

    }


    private Set<String> buildParts(String parenthesisContent, List<Integer> separator) {
        Set<String> parts = new HashSet<>();
        for (int i = 0; i <= separator.size(); i++) {
            if(i == 0) {
                parts.add(parenthesisContent.substring(0, separator.get(i)));
            } else if(i == separator.size()) {
                parts.add(parenthesisContent.substring(separator.get(i-1)+1));
            } else {
                parts.add(parenthesisContent.substring(separator.get(i-1)+1, separator.get(i)));
            }
        }
        return parts;
    }

    public Position drawMazePart(Position currentPosition, String todraw) {

        if(currentPosition.equals(new Position(0,0))) {
            maze.put(currentPosition, Route.START);
        }

        for (char c : todraw.toCharArray()) {
            Direction dir = Direction.from(c);
            switch (dir) {
                case WEST:
                    currentPosition = currentPosition.left();
                    maze.put(currentPosition, Route.VDOOR);
                    currentPosition = currentPosition.left();
                    maze.put(currentPosition, Route.PATH);
                    break;
                case EAST:
                    currentPosition = currentPosition.right();
                    maze.put(currentPosition, Route.VDOOR);
                    currentPosition = currentPosition.right();
                    maze.put(currentPosition, Route.PATH);
                    break;
                case NORTH:
                    currentPosition = currentPosition.up();
                    maze.put(currentPosition, Route.HDOOR);
                    currentPosition = currentPosition.up();
                    maze.put(currentPosition, Route.PATH);
                    break;
                case SOUTH:
                    currentPosition = currentPosition.down();
                    maze.put(currentPosition, Route.HDOOR);
                    currentPosition = currentPosition.down();
                    maze.put(currentPosition, Route.PATH);
                    break;
            }
        }
        return currentPosition;
    }

    public void print() {
        int minX = maze.keySet().stream().min(Comparator.comparing(p -> p.x)).map(p -> p.x).orElseThrow(RuntimeException::new);
        int maxX = maze.keySet().stream().max(Comparator.comparing(p -> p.x)).map(p -> p.x).orElseThrow(RuntimeException::new);
        int minY = maze.keySet().stream().min(Comparator.comparing(p -> p.y)).map(p -> p.y).orElseThrow(RuntimeException::new);
        int maxY = maze.keySet().stream().max(Comparator.comparing(p -> p.y)).map(p -> p.y).orElseThrow(RuntimeException::new);
        for (int i = minY; i <= maxY; i++) {
            System.out.println();
            for (int j = minX; j <= maxX; j++) {
                Position pos = new Position(j, i);
                Route route = maze.getOrDefault(pos, Route.WALL);
                System.out.print(route.sign);
            }
        }
    }

    public static int findClosing(String s, int n) {

        int counter = 0;
        int positionOfMatchingParen = -1;
        boolean found = false;
        while (n < s.length() && !found) {

            if (s.charAt(n) == (OPENING)) {
                counter++;
            } else if (s.charAt(n) == (CLOSING)) {
                counter--;
                if (counter == 0) {
                    positionOfMatchingParen = n;
                    found = true;
                }
            }
            n++;
        }
        return positionOfMatchingParen;
    }


    public static List<Integer> findSeparator(String s) {
            List<Integer> separators = new ArrayList<>();
        int n = 0;
        int counter = 0;
        int positionOfMatchingParen = -1;
        while (n < s.length()) {

            if (s.charAt(n) == OPENING) {
                counter++;
            } else if (s.charAt(n) == CLOSING) {
                counter--;
            } else if (s.charAt(n) == SEPARATOR) {
                if (counter == 0) {
                    separators.add(n);
                }
            }
            n++;
        }
        return separators;
    }

}
