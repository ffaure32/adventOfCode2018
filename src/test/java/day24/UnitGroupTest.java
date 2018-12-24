package day24;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitGroupTest {
    @Test
    public void initGroup() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
        UnitGroup ug = new UnitGroup("immune", input);

        assertEquals(144, ug.effectivePower());
    }

    //1209 units each with 34572 hit points with an attack that does 55 bludgeoning damage at initiative 3
    @Test
    public void initGroupWithoutWeak() {
        String input = "1209 units each with 34572 hit points with an attack that does 55 bludgeoning damage at initiative 3";
        UnitGroup ug = new UnitGroup("immune", input);

        assertEquals(66495, ug.effectivePower());
    }

}