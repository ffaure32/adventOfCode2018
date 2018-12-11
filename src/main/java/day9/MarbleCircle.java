package day9;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.LongStream;

public class MarbleCircle {
    long[] scores;
    long[] grid;
    int currentPlayer = 0;
    long nbPlayers;
    long currentMarble = 2;
    int currentIndex = 1;
    int realGridSize;
    Stopwatch sw;

    public MarbleCircle(int nbPlayers, int nbMarbles) {
        this.nbPlayers = nbPlayers;
        initScores(nbPlayers);
        initGrid(nbMarbles);
        sw = Stopwatch.createStarted();
    }

    private void initGrid(int nbMarbles) {
        grid = new long[nbMarbles];
        grid[0] = 0l;
        grid[1] = 1l;
        realGridSize = 1;
    }

    public void nextMarble() {
        if(currentMarble % 10000 == 0) {
            System.out.println(sw.elapsed());
        }
        nextPlayer();
        if(currentMarble %23 == 0) {
            currentIndex -= 7;
            if(currentIndex<0) {
                currentIndex = realGridSize+currentIndex;
            }
            long result = grid[currentIndex];
            grid = ArrayUtils.remove(grid, currentIndex);
            realGridSize--;
            long actualScore = scores[currentPlayer];
            actualScore += result;
            actualScore += currentMarble;
            scores[currentPlayer] = actualScore;
        } else {
            currentIndex += 2;
            if(currentIndex>realGridSize) {
                currentIndex = currentIndex - realGridSize;
            }
            grid[currentIndex] = currentMarble;
            realGridSize++;
        }
        currentMarble++;
    }

    public long maxScore() {
        return Arrays.stream(scores).max().orElseThrow(RuntimeException::new);
    }

    private void nextPlayer() {
        currentPlayer++;
        if(currentPlayer>=nbPlayers){
            currentPlayer = 0;
        }
    }

    private void initScores(int nbPlayers) {
        scores = new long[nbPlayers];
    }
}
