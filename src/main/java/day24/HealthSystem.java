package day24;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HealthSystem {
    public List<UnitGroup> groups;

    public HealthSystem(String type, List<String> input) {
        AtomicInteger counter = new AtomicInteger(1);
        groups = input.stream().map(s -> new UnitGroup(type+" group "+ counter.getAndIncrement(),s)).collect(Collectors.toList());
    }

    public Map<UnitGroup, UnitGroup> findTargets(HealthSystem opponents) {
        Map<UnitGroup, UnitGroup> targets = new HashMap<>();
        List<UnitGroup> sorted = groups.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sorted.stream().forEach(ug -> findBestOpponent(ug, opponents, targets));
        return targets;
    }

    private void findBestOpponent(UnitGroup ug, HealthSystem opponents, Map<UnitGroup, UnitGroup> targets) {
        Set<UnitGroup> potentialTargets = opponents.groups.stream()
                .filter(g -> !targets.values().contains(g))
                .filter(g -> !g.immunities.contains(ug.attackType))
                .collect(Collectors.toSet());
        potentialTargets = compareGroups(potentialTargets, ug.attackType);
        Optional<UnitGroup> potentialTarget = potentialTargets.stream()
                .sorted(Comparator.reverseOrder())
                .findFirst();
        potentialTarget.ifPresent(pt -> targets.put(ug, pt));
    }

    public Set<UnitGroup> compareGroups(Set<UnitGroup> groups, String attackType) {
        Set<UnitGroup> weakGroup = groups.stream().filter(g -> g.weaknesses.contains(attackType)).collect(Collectors.toSet());
        if(weakGroup.isEmpty()) {
            return groups;
        } else {
            return weakGroup;
        }
    }
}
