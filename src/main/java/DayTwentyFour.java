import day24.HealthSystem;
import day24.UnitGroup;

import java.util.*;
import java.util.stream.Collectors;

public class DayTwentyFour {
    public HealthSystem infectionSystem;
    public HealthSystem immuneSystem;

    public DayTwentyFour(List<String> immuneInput, List<String> infectionInput) {
        immuneSystem = new HealthSystem("Immune", immuneInput);
        infectionSystem = new HealthSystem("Infection", infectionInput);

    }

    public long fight() {
        boolean gameOver = false;
        while (!gameOver) {
            round();
            gameOver = isSystemDead(immuneSystem) || isSystemDead(infectionSystem);
        }
        return countUnits(immuneSystem) + countUnits(infectionSystem);
    }

    private boolean isSystemDead(HealthSystem system) {
        return system.groups.stream().noneMatch(g -> g.units > 0);
    }

    private long countUnits(HealthSystem system) {
        return system.groups.stream().map(g -> g.units).collect(Collectors.summingLong(l -> l));
    }

    public void round() {
        Map<UnitGroup, UnitGroup> immuneTargets = immuneSystem.findTargets(infectionSystem);
        Map<UnitGroup, UnitGroup> infectionTargets = infectionSystem.findTargets(immuneSystem);
        List<UnitGroup> orderedUnits = new ArrayList<>();
        orderedUnits.addAll(immuneTargets.keySet());
        orderedUnits.addAll(infectionTargets.keySet());
        Comparator<UnitGroup> comparing = Comparator.comparing(ou -> ou.initiative);
        Comparator<UnitGroup> reversed = comparing.reversed();
        Collections.sort(orderedUnits, reversed);

        orderedUnits.stream().filter(ug -> ug.units > 0).forEach(ug -> attackTarget(ug, immuneTargets, infectionTargets));
    }

    private void attackTarget(UnitGroup ug, Map<UnitGroup, UnitGroup> immuneTargets, Map<UnitGroup, UnitGroup> infectionTargets) {
        UnitGroup target = immuneTargets.get(ug);
        if (target == null) {
            target = infectionTargets.get(ug);
        }
        if (target != null) {
            target.attacked(ug);
            if(target.units == 0) {
                infectionSystem.groups.remove(target);
                immuneSystem.groups.remove(target);
            }
        }

    }
}
