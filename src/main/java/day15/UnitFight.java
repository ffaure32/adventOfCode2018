package day15;

import day10.Position;
import org.apache.commons.lang3.RandomUtils;

import java.util.Objects;

public class UnitFight implements Comparable<UnitFight> {
    public int id;
    public int attackPower = 3;
    public int hitPoints = 200;

    public UnitFight(Position position, UnitType type, int attackPowerForElves) {
        id = RandomUtils.nextInt();
        this.position = position;
        this.type = type;
        if(type == UnitType.ELF) {
            this.attackPower = attackPowerForElves;
        }
    }

    public boolean attacked(int attackPoints) {
        this.hitPoints -= attackPoints;
        return hitPoints<=0;
    }

    @Override
    public int compareTo(UnitFight o) {
        int result = this.position.compareTo(o.position);
        if(result == 0) {
            return this.id - o.id;
        }
        return result;
    }

    public enum UnitType {
        ELF,
        GOBELIN;
    }
    public Position position;
    public final UnitType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitFight unitFight = (UnitFight) o;
        return id == unitFight.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
