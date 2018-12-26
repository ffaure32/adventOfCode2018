package day24;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitGroup implements Comparable<UnitGroup> {
    private final String name;
    public long units;
    public Set<String> weaknesses = new HashSet<>();
    public Set<String> immunities = new HashSet<>();

    private Pattern pattern = Pattern.compile("([0-9]+) units each with ([0-9]+) hit points \\({1}(.*)\\){1} with an attack that does ([0-9]+) ([a-z]+) damage at initiative ([0-9]+)");
    private Pattern otherpattern = Pattern.compile("([0-9]+) units each with ([0-9]+) hit points with an attack that does ([0-9]+) ([a-z]+) damage at initiative ([0-9]+)");
    public int initiative;
    public String attackType;
    private int hitPoints;
    private int attackDamage;

    public UnitGroup(String name, String input) {
        this.name = name;
        Matcher matcher = pattern.matcher(input);
        int groupNumber = 1;
        if (!matcher.find()) {
            matcher = otherpattern.matcher(input);
            matcher.find();
        }
        int nbOfUnits = Integer.parseInt(matcher.group(groupNumber++));
        hitPoints = Integer.parseInt(matcher.group(groupNumber++));
        if (matcher.groupCount() == 6) {
            String weaknessesAndImmunities = matcher.group(groupNumber++);
            initImmunitiesAndWeaknesses(weaknessesAndImmunities);
        }
        attackDamage = Integer.parseInt(matcher.group(groupNumber++));
        attackType = matcher.group(groupNumber++);
        initiative = Integer.parseInt(matcher.group(groupNumber++));

        units = nbOfUnits;
    }

    public void boost(int boostPoints) {
        this.attackDamage+=boostPoints;
    }
    public void initImmunitiesAndWeaknesses(String input) {
        String[] split = input.split("; ");
        Arrays.stream(split).forEach(this::parseWeaknessOrImmunity);
    }

    private void parseWeaknessOrImmunity(String s) {
        if (s.contains("immune to")) {
            String[] immunities = s.replace("immune to ", "").split(", ");
            Collections.addAll(this.immunities, immunities);
        } else {
            String[] weaknesses = s.replace("weak to ", "").split(", ");
            Collections.addAll(this.weaknesses, weaknesses);
        }
    }

    public long effectivePower() {
        return units * attackDamage;
    }

    @Override
    public int compareTo(UnitGroup o) {
        int result = (int)(this.effectivePower() - o.effectivePower());
        if(result == 0) {
            result = this.initiative - o.initiative;
        }
        return result;
    }

    public void attacked(UnitGroup attacker) {
        units = Math.max(0l, units - damaged(attacker));

    }

    public long damaged(UnitGroup attacker) {
        int multiply = 1;
        if(weaknesses.contains(attacker.attackType)) {
            multiply = 2;
        }
        return attacker.effectivePower() * multiply / hitPoints;
    }


    @Override
    public String toString() {
        return name;
    }
}
