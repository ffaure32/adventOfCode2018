import day23.DayTwentyThree;
import day23.Nanobot;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    @Test
    public void findBotsInRangeRealInputPart2SimpleSolution() {
        DayTwentyThreeBis day23 = new DayTwentyThreeBis(InputLoader.loadInputList("inputDay23.txt"));
        day23.execute();
    }

    @Test
    public void findBotsInRangeRealInputPart2() {
        Long[] center = new Long[3];
        center[0] = 0l;
        center[1] = 0l;
        center[2] = 0l;
        DayTwentyThree day23 = new DayTwentyThree(InputLoader.loadInputList("inputDay23.txt"));
        Long[] position = day23.findAveragePos();
        System.out.println(position[0] + " " + position[1] + " " + position[2]+" "+day23.countInRange(position));

        Long[] oldposition = center;
        long counter = 0;
        while(!Arrays.equals(oldposition, position)) {
            counter++;
            oldposition = position;
            long inRange = day23.countInRange(position);
            Set<Long[]> newPositions = day23.aroundPositions(position);

            for (Long[] pos : newPositions) {
                long newInRange = day23.countInRange(pos);
                if (newInRange > inRange) {
                    position = pos;
                    inRange = newInRange;
                    System.out.println(newInRange);
                } else if (newInRange == inRange) {
                    long olddistance = Nanobot.computeDistance(center, position);
                    long newdistance = Nanobot.computeDistance(center, pos);
                    if (newdistance < olddistance) {
                        position = pos;
                    }

                }
            }
            if(counter % 10000 == 0) {
                System.out.println(position[0] + " " + position[1] + " " + position[2]+" "+inRange);
            }
        }
        System.out.println(position[0] + " " + position[1] + " " + position[2]+" "+day23.countInRange(position));
    }

}
