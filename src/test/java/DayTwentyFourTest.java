import day24.HealthSystem;
import day24.UnitGroup;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class DayTwentyFourTest {
    @Test
    public void testSample() {
        List<String> immuneInput = Lists.newArrayList(
                "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
                "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
        );

        List<String> infectionInput = Lists.newArrayList(
                "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1",
                "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4"
        );

        DayTwentyFour day24 = new DayTwentyFour(immuneInput, infectionInput);

        assertEquals(5216l, day24.fight());
    }

    @Test
    public void testRealInput() {
        List<String> immuneInput = InputLoader.loadInputList("immuneInputDay24.txt");

        List<String> infectionInput = InputLoader.loadInputList("infectionInputDay24.txt");

        DayTwentyFour day24 = new DayTwentyFour(immuneInput, infectionInput);

        assertEquals(5216l, day24.fight());
    }

}
