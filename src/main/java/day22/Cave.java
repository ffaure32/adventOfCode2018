package day22;

import day10.Position;

import java.util.HashMap;
import java.util.Map;

public class Cave {
    private final int depth;
    private final Position target;
    private Map<Position, Long> erosionLevels;

    public Cave(int depth, Position target) {

        this.depth = depth;
        this.target = target;
        initErosionLevels();
    }

    private void initErosionLevels() {
        erosionLevels = new HashMap<>();
        for (int i = 0; i <= target.y * 7; i++) {
            for (int j = 0; j <= target.x * 7; j++) {
                Position position = new Position(j, i);
                erosionLevels.put(position, erosionLevel(position));
            }
        }
    }

    public long erosionLevel(Position position) {
        return (geologicalIndex(position)+depth) % 20183;
    }

    public long geologicalIndex(Position position) {
        return geologicalIndex(position.x, position.y);
    }
    public long geologicalIndex(int x, int y) {
        if(x == 0  && y == 0) {
            return 0;
        }
        if(x == target.x && y == target.y) {
            return 0;
        }
        if(y == 0) {
            return x * 16807l;
        }
        if(x == 0) {
            return y * 48271l;
        }
        return erosionLevels.get(new Position(x-1, y)) * erosionLevels.get(new Position(x, y-1));
    }

    public long riskLevel() {
        return erosionLevels.entrySet()
                .stream().filter(es -> es.getKey().x <= target.x
                && es.getKey().y <= target.y).map(es -> es.getValue()).mapToLong(el -> el %3).sum();
    }

    public void print() {
        for (int i = 0; i < target.y; i++) {
            System.out.println();
            for (int j = 0; j < target.x; j++) {
                Long el = erosionLevels.get(new Position(j, i));
                long modulo = el % 3;
                switch ((int)modulo) {
                    case 0:
                        System.out.print('.');
                        break;
                    case 1:
                        System.out.print('=');
                        break;
                    case 2:
                        System.out.print('|');
                        break;
                    default:
                }
                if(modulo == 0) {
                }

            }
        }
    }
}
