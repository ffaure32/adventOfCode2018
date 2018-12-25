package day20;

import day10.Position;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Maze {
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

    private Set<String> pathes;
    public Maze(String input) {
        pathes = cutInput(input.substring(1, input.length()-1));
    }

    public void drawMaze() {
        for(String path: pathes) {
            System.out.println(path);
            System.out.println(path.length());
            Position currentPosition = new Position(0, 0);
            maze.put(currentPosition, Route.START);

            for (char c : path.toCharArray()) {
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
        }
    }

    public Set<String> cutInput(String input) {
        Set<String> newResults = new HashSet<>();
        Set<String> result = getInputs(input);
        if(result.size()> 1) {
            for(String newResult : result) {
                newResults.addAll(cutInput(newResult));
            }
        } else {
            newResults.addAll(result);
        }
        return newResults;
    }

    @NotNull
    private Set<String> getInputs(String input) {
        Set<String> result = new HashSet<>();
        int firstParenthesis  = input.indexOf("(");
        if(firstParenthesis>=0) {
            int close = findClosing(input, firstParenthesis);
            String parenthesisContent = input.substring(firstParenthesis+1, close);
            String[] split = parenthesisContent.split("\\|", 2);
            String firstInput = input.substring(0, firstParenthesis)+split[0]+input.substring(close+1);
            result.add(firstInput);
            String secondInput = input.substring(0, firstParenthesis)+split[1]+input.substring(close+1);
            result.add(secondInput);
        } else {
            result.add(input);
        }
        return result;
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
        char opening = '(';
        char closing = ')';
        int positionOfMatchingParen = -1;
        boolean found = false;
        while(n < s.length() && !found) {

            if(s.charAt(n) == (opening)) {
                counter++;
            }
            else if(s.charAt(n) == (closing)) {
                counter--;
                if(counter == 0) {
                    positionOfMatchingParen = n;
                    found = true;
                }
            }
            n++;
        }
        return positionOfMatchingParen;
    }
}
