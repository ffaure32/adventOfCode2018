package day9;

import java.util.Arrays;

public class MarbleCircle {
    long[] scores;
    long[] grid;
    int currentPlayer = 0;
    long nbPlayers;
    int currentMarbleIndex = 0;

    Marble currentMarble;

    public MarbleCircle(int nbPlayers, int nbMarbles) {
        this.nbPlayers = nbPlayers;
        initScores(nbPlayers);

        currentMarble = new Marble(currentMarbleIndex++);
        currentMarble.setNext(currentMarble);
        currentMarble.setPrevious(currentMarble);
    }

    public void nextMarble() {
        nextPlayer();
        if(currentMarbleIndex %23 == 0) {
            long result = getMarbleScoreAndRemove(7);
            updateScore(result);
        } else {
            insertMarble(2);
        }
        currentMarbleIndex++;
    }

    private void updateScore(long result) {
        long actualScore = scores[currentPlayer];
        actualScore += result;
        actualScore += currentMarbleIndex;
        scores[currentPlayer] = actualScore;
    }

    private long getMarbleScoreAndRemove(int moves) {
        Marble toRemove = currentMarble.moveLeft(moves);
        return remove(toRemove);
    }

    private long remove(Marble toRemove) {
        long result = toRemove.getMarbleIndex();
        currentMarble = toRemove.getNext();
        currentMarble.setPrevious(toRemove.getPrevious());
        return result;
    }

    private void insertMarble(int moves) {
        Marble newMarble = new Marble(currentMarbleIndex);
        Marble right = currentMarble.moveRight(moves);
        right.insertBefore(newMarble);
        currentMarble = newMarble;
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
