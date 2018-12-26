import day20.Maze;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayTwentyTest {
    @Test
    public void testInit() {
        Maze maze = new Maze("^WNE$");
        //maze.drawMaze();
        maze.print();
    }

    @Test
    public void testSplit() {
        Maze maze = new Maze("^WNE(ENE|ESE)NWN$");
        //maze.drawMaze();
        maze.print();
    }

    @Test
    public void testSample() {
        Maze maze = new Maze("^ENWWW(NEEE|SSE(EE|N))$");
        //maze.drawMaze();
        maze.print();
    }

    @Test
    public void testSample2() {
        Maze maze = new Maze("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
        //maze.drawMaze();
        maze.print();
    }

    @Test
    public void testRealInput() {
        List<String> strings = InputLoader.loadInputList("inputDay20.txt");
        Maze maze = new Maze(strings.get(0));
        // maze.drawMaze();
        maze.print();
    }

    @Test
    public void testFindSeparator() {
        String test = "WES|EST";
        assertEquals(3, Maze.findSeparator(test));
    }

    @Test
    public void testFindSeparatorWithInnerParenthesis() {
        String test = "WE(WES|EST)WES|EST";
        assertEquals(14, Maze.findSeparator(test));
    }

}
