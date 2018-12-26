import day23.DayTwentyThree;
import day23.Nanobot;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayTwentyThreeTest {
    private List<String> sample = Lists.newArrayList(
            "pos=<0,0,0>, r=4",
            "pos=<1,0,0>, r=1",
            "pos=<4,0,0>, r=3",
            "pos=<0,2,0>, r=1",
            "pos=<0,5,0>, r=3",
            "pos=<0,0,3>, r=1",
            "pos=<1,1,1>, r=1",
            "pos=<1,1,2>, r=1",
            "pos=<1,3,1>, r=1"
    );

    @Test
    public void warmup() {
        Nanobot nanobot = new Nanobot("pos=<64962278,33699509,27360131>, r=60979411");
        assertEquals(60979411, nanobot.radius);
        assertEquals(64962278, nanobot.position[0].longValue());
        assertEquals(33699509, nanobot.position[1].longValue());
        assertEquals(27360131, nanobot.position[2].longValue());
    }

    @Test
    public void findStrongest() {
        DayTwentyThree day23 = new DayTwentyThree(sample);
        Nanobot strongest = day23.findStrongest();
        assertEquals(4, strongest.radius);
        assertEquals(0, strongest.position[0].longValue());
        assertEquals(0, strongest.position[1].longValue());
        assertEquals(0, strongest.position[2].longValue());
    }

    @Test
    public void findBotsInRange() {
        DayTwentyThree day23 = new DayTwentyThree(sample);
        Nanobot strongest = day23.findStrongest();
        long result = day23.countInRange(strongest);
        assertEquals(7, result);
    }

    @Test
    public void findBotsInRangeRealInput() {
        DayTwentyThree day23 = new DayTwentyThree(InputLoader.loadInputList("inputDay23.txt"));
        Nanobot strongest = day23.findStrongest();
        long result = day23.countInRange(strongest);
        assertEquals(463, result);
    }

}
