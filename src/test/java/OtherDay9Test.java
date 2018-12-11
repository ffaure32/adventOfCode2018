import day9.MarbleCircle;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class OtherDay9Test {
    @Test
    public void testRealInputPart2() {
        int nbPlayers = 486;
        int nbMarbles = 7083300;
        long expectedResult = 2954067253l;
        verifyData(nbPlayers, nbMarbles, expectedResult);
    }

    @Test
    public void testAllSamples() {
        verifyData(10, 1618, 8317);
        verifyData(13, 7999, 146373);
        verifyData(21, 6111, 54718);
        verifyData(30, 5807, 37305);
        verifyData(17, 1104, 2720);
    }

    @Test
    public void testInitData() {
        verifyData(9, 25, 32);
    }

    @Test
    public void testRealInputPart1() {
        verifyData(486, 70833, 373597);
    }


    private void verifyData(int nbPlayers, int nbMarbles, long expectedResult) {
        MarbleCircle marble = new MarbleCircle(nbPlayers, nbMarbles);
        for (int i = 2; i < nbMarbles; i++) {
            marble.nextMarble();
        }
        assertEquals(expectedResult, marble.maxScore());
    }
}
