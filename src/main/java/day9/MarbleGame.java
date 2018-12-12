package day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MarbleGame {
    private final List<BigInteger> grid;
    private final Map<Integer, BigInteger> score;
    private int numberOfMarbles;
    private int marblePlayed;
    private int currentMarbleIndex;
    private int currentPlayer;

    public MarbleGame(int numberOfPlayers, int numberOfMarbles) {
        this.numberOfMarbles = numberOfMarbles;
        this.marblePlayed = 0;
        this.currentMarbleIndex = 0;
        this.currentPlayer = 0;

        grid = new ArrayList<>();

        grid.add(BigInteger.ZERO);
        score = new HashMap<>(numberOfPlayers);

        initScore(numberOfPlayers);
    }

    public void playRound() {
        nextPlayer();
        if(gameOver()) {
            throw new RuntimeException();
        }
        int marbleToPlay = ++marblePlayed;

        if(marbleToPlay % 23 == 0) {
            currentMarbleIndex -= 7;
            if(currentMarbleIndex<0) {
                currentMarbleIndex = grid.size()+currentMarbleIndex;
            }
            BigInteger scoreForPlayer = score.get(currentPlayer);
            scoreForPlayer = scoreForPlayer.add(BigInteger.valueOf(marbleToPlay)).add(grid.remove(currentMarbleIndex));
            score.put(currentPlayer, scoreForPlayer);
        } else {
            if(marbleToPlay == 1) {
                currentMarbleIndex += 1;
            } else {
                currentMarbleIndex += 2;
            }
            if(currentMarbleIndex > grid.size()) {
                currentMarbleIndex = currentMarbleIndex-grid.size();
            }
            grid.add(currentMarbleIndex, BigInteger.valueOf(marbleToPlay));
        }
    }

    private void nextPlayer() {
        currentPlayer++;
        if(currentPlayer > score.size()) {
            currentPlayer = 1;
        }
    }

    public int maxScore() {
        return score.values().stream().map(ai -> ai.intValue()).mapToInt(i -> i).max().orElseThrow(RuntimeException::new);
    }

    public boolean gameOver() {
        return marblePlayed == numberOfMarbles;
    }

    private void initScore(int numberOfPlayers) {
        IntStream.rangeClosed(1, numberOfPlayers).forEach(i -> score.put(i, BigInteger.ZERO));
    }

    public List<BigInteger> getGrid() {
        return grid;
    }

    public Map<Integer, BigInteger> getScore() {
        return score;
    }

    public int getCurrentMarbleIndex() {
        return currentMarbleIndex;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
