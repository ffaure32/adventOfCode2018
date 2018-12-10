import day10.LightPoint;

import java.util.List;
import java.util.stream.Collectors;

public class DayTen {
    private List<LightPoint> lightPoints;
    LightPointGrid lpg;

    public DayTen(List<String> input, int letterHeight) {
        lightPoints = input.stream().map(s -> new LightPoint(s)).collect(Collectors.toList());
        lpg =  new LightPointGrid(lightPoints, letterHeight);
    }

    public void movePoints() {
        lightPoints.stream().forEach(LightPoint::move);
    }

    public boolean print() {
        lpg.update(lightPoints);
        return lpg.print();
    }
}
