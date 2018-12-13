package day13;

public enum Turn {
    LEFT, STRAIGHT, RIGHT;

    public static Turn nextTurn(Turn nextTurn) {
        switch (nextTurn) {
            case LEFT:
                return STRAIGHT;
            case STRAIGHT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        throw new RuntimeException();
    }
}
