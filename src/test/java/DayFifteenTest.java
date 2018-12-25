import com.google.common.collect.Iterables;
import day10.Position;
import day15.FightMap;
import day15.UnitFight;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DayFifteenTest {

    private List<String> testInput;

    @Before
    public void setUp() throws Exception {
        testInput = Lists.newArrayList(
            "#######",
                "#.G.E.#",
                "#E.G.E#",
                "#.G.E.#",
                "#######");
    }

    @Test
    public void initModel() {

        FightMap fightMap = new FightMap(testInput);
        Set<UnitFight> fighters = fightMap.getUnits();

        assertEquals(Iterables.get(fighters, 0).position, new Position(2, 1));
        assertEquals(Iterables.get(fighters, 1).position, new Position(4, 1));
        assertEquals(Iterables.get(fighters, 2).position, new Position(1, 2));
        assertEquals(Iterables.get(fighters, 3).position, new Position(3, 2));
        assertEquals(Iterables.get(fighters, 4).position, new Position(5, 2));
        assertEquals(Iterables.get(fighters, 5).position, new Position(2, 3));
        assertEquals(Iterables.get(fighters, 6).position, new Position(4, 3));
    }

    @Test
    public void firstRoundForFirstUnit() {
        List<String> otherInput = Lists.newArrayList(
                "#########",
                "#G..G..G#",
                "#.......#",
                "#.......#",
                "#G..E..G#",
                "#.......#",
                "#.......#",
                "#G..G..G#",
                "#########")
        ;
        FightMap fightMap = new FightMap(otherInput);
        for (int i = 0; i < 3; i++) {
            fightMap.playRound();
            fightMap.print();
        }
    }

    @Test
    public void tryAttack() {
        List<String> otherInput = Lists.newArrayList(
                "#######",
                "#.G...#",
                "#...EG#",
                "#.#.#G#",
                "#..G#E#",
                "#.....#",
                "#######")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(27730, fightMap.score());
    }

    @Test
    public void sample1() {
        List<String> otherInput = Lists.newArrayList(
                "#######",
                "#G..#E#",
                "#E#E.E#",
                "#G.##.#",
                "#...#E#",
                "#...E.#",
                "#######")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(36334, fightMap.score());
    }

    @Test
    public void sample2() {
        List<String> otherInput = Lists.newArrayList(
                "#######",
                "#E..EG#",
                "#.#G.E#",
                "#E.##E#",
                "#G..#.#",
                "#..E#.#",
                "#######")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(39514, fightMap.score());
    }

    @Test
    public void sample3() {
        List<String> otherInput = Lists.newArrayList(
                "#######",
                "#E.G#.#",
                "#.#G..#",
                "#G.#.G#",
                "#G..#.#",
                "#...E.#",
                "#######")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(27755, fightMap.score());
    }

    @Test
    public void sample4() {
        List<String> otherInput = Lists.newArrayList(
                "#######",
                "#.E...#",
                "#.#..G#",
                "#.###.#",
                "#E#G#G#",
                "#...#G#",
                "#######")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(28944, fightMap.score());
    }

    @Test
    public void sample5() {
        List<String> otherInput = Lists.newArrayList(
                "#########",
                "#G......#",
                "#.E.#...#",
                "#..##..G#",
                "#...##..#",
                "#...#...#",
                "#.G...G.#",
                "#.....G.#",
                "#########")
                ;
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(18740, fightMap.score());
    }

    @Test
    public void testRealInput() {
        List<String> otherInput = InputLoader.loadInputList("inputDay15.txt");
        FightMap fightMap = new FightMap(otherInput);
        boolean fightOvers = false;
        while (!fightOvers) {
            fightMap.playRound();
            fightMap.print();
            fightMap.printPoints();
            fightOvers = fightMap.isFightOver();
        }
        assertEquals(39514, fightMap.score());
    }

    @Test
    public void testRealInputPart2() {
        List<String> otherInput = InputLoader.loadInputList("inputDay15.txt");
        boolean elvesWithoutLoss = false;
        int attackPower = 4;
        while(!elvesWithoutLoss) {
            FightMap fightMap = new FightMap(otherInput, attackPower);
            boolean fightOvers = false;
            while (!fightOvers) {
                fightMap.playRound();
                //fightMap.print();
                //fightMap.printPoints();
                fightOvers = fightMap.isFightOver();
            }
            elvesWithoutLoss = fightMap.countElves() == fightMap.initNumberOfElves;
            System.out.println("elve loss"+ (fightMap.initNumberOfElves-fightMap.countElves()));
            System.out.println(fightMap.score());
            attackPower++;
        }
    }
}
