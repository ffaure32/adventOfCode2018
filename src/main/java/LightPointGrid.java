import day10.LightPoint;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LightPointGrid {
    public static final char CHAR_IF_PRESENT = '#';
    public static final char CHAR_IF_ABSENT = '.';
    List<LightPoint> lightPoints;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private final int letterHeight;


    public LightPointGrid(List<LightPoint> lightPoints, int letterHeight) {
        minX = lightPoints.stream().min(Comparator.comparing(lp -> lp.getPos().x)).map(lp -> lp.getPos().x).orElseThrow(RuntimeException::new);
        maxX = lightPoints.stream().max(Comparator.comparing(lp -> lp.getPos().x)).map(lp -> lp.getPos().x).orElseThrow(RuntimeException::new);
        minY = lightPoints.stream().min(Comparator.comparing(lp -> lp.getPos().y)).map(lp -> lp.getPos().y).orElseThrow(RuntimeException::new);
        maxY = lightPoints.stream().max(Comparator.comparing(lp -> lp.getPos().y)).map(lp -> lp.getPos().y).orElseThrow(RuntimeException::new);

        this.letterHeight = letterHeight;
    }

    public void update(List<LightPoint> lightPoints) {
        this.lightPoints = lightPoints;
        minX = lightPoints.stream().min(Comparator.comparing(lp -> lp.getPos().x)).map(lp -> lp.getPos().x).orElseThrow(RuntimeException::new);
        maxX = lightPoints.stream().max(Comparator.comparing(lp -> lp.getPos().x)).map(lp -> lp.getPos().x).orElseThrow(RuntimeException::new);
        minY = lightPoints.stream().min(Comparator.comparing(lp -> lp.getPos().y)).map(lp -> lp.getPos().y).orElseThrow(RuntimeException::new);
        maxY = lightPoints.stream().max(Comparator.comparing(lp -> lp.getPos().y)).map(lp -> lp.getPos().y).orElseThrow(RuntimeException::new);
    }


    public boolean print() {

        int maxHeight = maxY + 1 - minY;
        if(maxHeight<=letterHeight) {
            for (int i = 0; i < maxHeight; i++) {
                for (int j = 0; j < maxX + 1 - minX; j++) {
                    int finalI = i + minY;
                    int finalJ = j + minX;
                    Optional<LightPoint> first = lightPoints.stream().filter(lp -> lp.getPos().y == finalI && lp.getPos().x == finalJ).findFirst();
                    Character toPrint = first.map(lp -> CHAR_IF_PRESENT).orElse(CHAR_IF_ABSENT);
                    System.out.print(toPrint);
                }
                System.out.println();
            }
            return true;
        }
        return false;
    }


}
