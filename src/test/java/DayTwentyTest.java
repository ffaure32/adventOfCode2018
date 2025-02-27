import day20.Maze;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
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
        int distance = maze.findFurthestRoom();
        assertEquals(10, distance);
        assertEquals(11, maze.farRooms.size());


    }

    @Test
    public void testSample2() {
        Maze maze = new Maze("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
        //maze.drawMaze();
        maze.print();
        int distance = maze.findFurthestRoom();
        assertEquals(18, distance);
    }

    @Test
    public void testSample3() {
        Maze maze = new Maze("^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$");
        //maze.drawMaze();
        maze.print();
        int distance = maze.findFurthestRoom();
        assertEquals(23, distance);
    }

    @Test
    public void testSample4() {
        Maze maze = new Maze("^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$");
        //maze.drawMaze();
        maze.print();
        int distance = maze.findFurthestRoom();
        assertEquals(31, distance);
    }

    @Test
    public void testRealInput() {
        List<String> strings = InputLoader.loadInputList("inputDay20.txt");
        Maze maze = new Maze(strings.get(0));
        // maze.drawMaze();
        // maze.print();
        int distance = maze.findFurthestRoom();
        assertEquals(3699, distance);
        assertEquals(11, maze.countRooms);
    }

    @Test
    public void testFindSeparator() {
        String test = "WES|EST";
        assertEquals(Lists.newArrayList(3), Maze.findSeparator(test));
    }

    @Test
    public void testFindSeparatorWithInnerParenthesis() {
        String test = "WE(WES|EST)WES|EST";
        assertEquals(Lists.newArrayList(14), Maze.findSeparator(test));
    }

    @Test
    public void testFindSeveralSeparators() {
        String test = "WES|EST|WES";
        assertEquals(Lists.newArrayList(3, 7), Maze.findSeparator(test));
    }

}
