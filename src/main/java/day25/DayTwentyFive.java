package day25;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class DayTwentyFive {
    public Set<Set<Star>> constellations = new HashSet<>();
    public void addStar(Star star) {
        Set<Set<Star>> collect = constellations.stream().filter(set -> isInSet(set,star)).collect(toSet());
        Set<Star> newConstellation = new HashSet<>();
        for(Set<Star> constellation : collect) {
            constellations.remove(constellation);
            newConstellation.addAll(constellation);
        }
        newConstellation.add(star);
        constellations.add(newConstellation);
    }

    private boolean isInSet(Set<Star> set, Star star) {
        return set.stream().anyMatch(s -> manhattanDistanceMax3(s, star));
    }

    private boolean manhattanDistanceMax3(Star s, Star star) {
        return Math.abs(s.a - star.a)
                + Math.abs(s.b - star.b) + Math.abs(s.c - star.c)+Math.abs(s.d - star.d) <=3;
    }
}
