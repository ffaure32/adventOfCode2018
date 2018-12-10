import day10.LightPoint;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LightPointGrid {
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
        //System.out.println(maxHeight);
        if(maxHeight<=letterHeight) {
            for (int i = 0; i < maxHeight; i++) {
                for (int j = 0; j < maxX + 1 - minX; j++) {
                    int finalI = i + minY;
                    int finalJ = j + minX;
                    Optional<LightPoint> first = lightPoints.stream().filter(lp -> lp.getPos().y == finalI && lp.getPos().x == finalJ).findFirst();
                    if (first.isPresent()) {
                        System.out.print('#');
                    } else {
                        System.out.print('.');
                    }
                }
                System.out.println();
            }
            return true;
        }
        return false;
    }


}
