import day17.GroundMap;

import java.util.List;

public class DaySeventeen {

    public GroundMap fillGroundMap(List<String> lines) {
        GroundMap map = new GroundMap(lines);
        map.initSpring();
        boolean active = true;
        while(active) {
            map.spreadWater();
            active = map.active();
        }
        return map;
    }


}
