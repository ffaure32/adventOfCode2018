package day11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FuelGrid {
    public static final int GRID_SIZE = 300;
    public static final int SQUARE_SIZE = 3;
    private FuelCell[][] cells;

    public FuelGrid(int gridSerialNumber) {
        FuelCell.gridSerialNumber = gridSerialNumber;
        cells = new FuelCell[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                FuelCell fc = new FuelCell(j+1, i+1);
                cells[j][i] = fc;
            }
        }
    }

    public SquarePosition findPowerfulSquare() {
        int squareSize = SQUARE_SIZE;
        Map<SquarePosition, Integer> powerPerSquare = buildMap(squareSize);
        return powerPerSquare.entrySet().stream().max(Comparator.comparing(es -> es.getValue())).map(es -> es.getKey()).orElseThrow(RuntimeException::new);
    }

    public SquarePosition findPowerfulSquareOfAnySize() {
        Map<SquarePosition, Integer> powerPerSquare = new HashMap<>();
        IntStream.range(1, 300).forEach(i -> powerPerSquare.putAll(buildMap(i)));
        return powerPerSquare.entrySet().stream().max(Comparator.comparing(es -> es.getValue())).map(es -> es.getKey()).orElseThrow(RuntimeException::new);
    }

    private Map<SquarePosition, Integer> buildMap(int squareSize) {
        Map<SquarePosition, Integer> powerPerSquare = new HashMap<>();
        for (int i = 0; i < GRID_SIZE - squareSize; i++) {
            for (int j = 0; j < GRID_SIZE - squareSize; j++) {
                SquarePosition pos = new SquarePosition(j + 1, i + 1, squareSize);
                powerPerSquare.put(pos, getPowerFromPosition(pos));
            }
        }
        return powerPerSquare;
    }


    private int getPowerFromPosition(SquarePosition pos) {
        int result = 0;
        for (int i = 0; i < pos.size; i++) {
            for (int j = 0; j < pos.size; j++) {
                FuelCell fuelCell = cells[pos.y+j-1][pos.x+i-1];
                result += fuelCell.powerLevel;
            }
        }
        return result;
    }
}
