import day9.MarbleGame;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DayNineTest {

    @Test
    public void initGame() {
        MarbleGame game = new MarbleGame(9, 25);

        game.playRound();

        assertEquals(1, game.getCurrentMarbleIndex());
        assertEquals(1, game.getCurrentPlayer());
        long[] expected = {0, 1};
        //assertArrayEquals(expected, game.getGrid());
    }

    @Test
    public void secondRound() {
        MarbleGame game = new MarbleGame(9, 25);

        game.playRound();
        game.playRound();

        assertEquals(1, game.getCurrentMarbleIndex());
        assertEquals(2, game.getCurrentPlayer());
        long[] expected = {0, 2, 1};
        //assertArrayEquals(expected, game.getGrid());
    }

    @Test
    public void thirdRound() {
        MarbleGame game = new MarbleGame(9, 25);

        game.playRound();
        game.playRound();
        game.playRound();

        assertEquals(3, game.getCurrentMarbleIndex());
        assertEquals(3, game.getCurrentPlayer());
        long[] expected = {0, 2, 1, 3};

        //assertArrayEquals(expected, game.getGrid());
    }

    @Test
    public void justBeforeModulo() {
        MarbleGame game = new MarbleGame(9, 25);

        IntStream.range(0, 22).forEach(i -> game.playRound());

        assertEquals(13, game.getCurrentMarbleIndex());
        assertEquals(4, game.getCurrentPlayer());
        long[] expected = {0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        //assertArrayEquals(expected, game.getGrid());
    }

    @Test
    public void justForModulo() {
        MarbleGame game = new MarbleGame(9, 25);

        IntStream.range(0, 23).forEach(i -> game.playRound());

        assertEquals(6, game.getCurrentMarbleIndex());
        assertEquals(5, game.getCurrentPlayer());
        long[] expected = {0, 16, 8, 17, 4, 18, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        //assertArrayEquals(expected, game.getGrid());
        assertEquals(32, game.maxScore());
    }


    @Test
    public void testSamples() {
        testEntry(10, 1618, 8317);
        testEntry(13, 7999, 146373);
        testEntry(17, 1104, 2764);
        testEntry(21, 6111, 54718);
        testEntry(30, 5807, 37305);
    }

    @Test
    public void testRealInput() {
        testEntry(486, 70833, 373597);
        testEntry(405, 71700, 428690);
        testEntry(405, 7170000, 428690);
    }

    @Test
    public void testRealInputPart2() {
        testEntry(486, 7083300, 373597);
    }

    private void testEntry(int numberOfPlayers, int numberOfMarbles, long expectedMaxScore) {
        MarbleGame game = new MarbleGame(numberOfPlayers, numberOfMarbles);
        while(!game.gameOver()) {
            game.playRound();
        }
        assertEquals(expectedMaxScore, game.maxScore());

    }
}
