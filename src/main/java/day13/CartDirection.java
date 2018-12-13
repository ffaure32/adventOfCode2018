package day13;

import java.util.Arrays;

public enum CartDirection {
    LEFT('<', PathType.HORIZONTAL),
    RIGHT('>', PathType.HORIZONTAL),
    UP('^', PathType.VERTICAL),
    DOWN('v', PathType.VERTICAL);


    public final char dirChar;
    public final PathType pathType;

    CartDirection(char c, PathType pt) {
        this.dirChar = c;
        this.pathType = pt;
    }

    public PathType getPathType() {
        return pathType;
    }

    public static CartDirection fromType(char c) {
        return Arrays.stream(CartDirection.values()).filter(pt -> pt.dirChar == c).findFirst().orElse(null);
    }

    public static CartDirection nextDirectionForSlash(CartDirection actual) {
        switch (actual) {
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            default:
                return actual;
        }
    }

    public static CartDirection nextDirectionForBacklash(CartDirection actual) {
        switch (actual) {
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            default:
                return actual;
        }
    }

    public static CartDirection nextDirectionForIntersection(CartDirection actual, Turn turn) {
        if (turn == Turn.STRAIGHT) {
            return actual;
        } else if (turn == Turn.LEFT) {
            switch (actual) {
                case LEFT:
                    return DOWN;
                case RIGHT:
                    return UP;
                case UP:
                    return LEFT;
                case DOWN:
                    return RIGHT;
                default:
                    return actual;
            }
        } else if (turn == Turn.RIGHT) {
            switch (actual) {
                case LEFT:
                    return UP;
                case RIGHT:
                    return DOWN;
                case UP:
                    return RIGHT;
                case DOWN:
                    return LEFT;
                default:
                    return actual;
            }
        }
        return actual;

    }
}
