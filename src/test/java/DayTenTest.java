import day10.LightPoint;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DayTenTest {

    @Test
    public void testBuildLightPoint() {
        String input = "position=< 9,  1> velocity=< 0,  2>";
        LightPoint lp = new LightPoint(input);

        assertEquals(9, lp.getPos().x);
        assertEquals(1, lp.getPos().y);
        assertEquals(0, lp.getVelocity().xSpeed);
        assertEquals(2, lp.getVelocity().ySpeed);
    }

    @Test
    public void testBuildLightPointWithNegativeCoords() {
        String input = "position=<-3, 11> velocity=< 1, -2>";
        LightPoint lp = new LightPoint(input);

        assertEquals(-3, lp.getPos().x);
        assertEquals(11, lp.getPos().y);
        assertEquals(1, lp.getVelocity().xSpeed);
        assertEquals(-2, lp.getVelocity().ySpeed);
    }

    @Test
    public void testMove() {
        String input = "position=<-3, 11> velocity=< 1, -2>";
        LightPoint lp = new LightPoint(input);

        lp.move();

        assertEquals(-2, lp.getPos().x);
        assertEquals(9, lp.getPos().y);
    }

    @Test
    public void testInputSample() {
        List<String> input = Lists.newArrayList(
                "position=< 9,  1> velocity=< 0,  2>",
                "position=< 7,  0> velocity=<-1,  0>",
                "position=< 3, -2> velocity=<-1,  1>",
                "position=< 6, 10> velocity=<-2, -1>",
                "position=< 2, -4> velocity=< 2,  2>",
                "position=<-6, 10> velocity=< 2, -2>",
                "position=< 1,  8> velocity=< 1, -1>",
                "position=< 1,  7> velocity=< 1,  0>",
                "position=<-3, 11> velocity=< 1, -2>",
                "position=< 7,  6> velocity=<-1, -1>",
                "position=<-2,  3> velocity=< 1,  0>",
                "position=<-4,  3> velocity=< 2,  0>",
                "position=<10, -3> velocity=<-1,  1>",
                "position=< 5, 11> velocity=< 1, -2>",
                "position=< 4,  7> velocity=< 0, -1>",
                "position=< 8, -2> velocity=< 0,  1>",
                "position=<15,  0> velocity=<-2,  0>",
                "position=< 1,  6> velocity=< 1,  0>",
                "position=< 8,  9> velocity=< 0, -1>",
                "position=< 3,  3> velocity=<-1,  1>",
                "position=< 0,  5> velocity=< 0, -1>",
                "position=<-2,  2> velocity=< 2,  0>",
                "position=< 5, -2> velocity=< 1,  2>",
                "position=< 1,  4> velocity=< 2,  1>",
                "position=<-2,  7> velocity=< 2, -2>",
                "position=< 3,  6> velocity=<-1, -1>",
                "position=< 5,  0> velocity=< 1,  0>",
                "position=<-6,  0> velocity=< 2,  0>",
                "position=< 5,  9> velocity=< 1, -2>",
                "position=<14,  7> velocity=<-2,  0>",
                "position=<-3,  6> velocity=< 2, -1>");

        DayTen dayThen = new DayTen(input, 8);
        boolean printed;
        int seconds = 0;
        do {
            dayThen.movePoints();
            printed = dayThen.print();
            seconds++;
        } while(!printed);
        assertEquals(3, seconds);
    }

    @Test
    public void testRealInput() {
        List<String> input = InputLoader.loadInputList("inputDay10.txt");
        DayTen dayThen = new DayTen(input, 10);
        boolean printed;
        int seconds = 0;
        do {
            dayThen.movePoints();
            printed = dayThen.print();
            seconds++;
        } while(!printed);
        assertEquals(10946, seconds);
    }

}
