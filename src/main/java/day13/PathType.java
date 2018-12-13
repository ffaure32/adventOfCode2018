package day13;

import java.util.Arrays;

public enum PathType {
    EMPTY(' '),
    HORIZONTAL('-'),
    VERTICAL('|'),
    SLASH('/'),
    BACKSLASH('\\'),
    INTERSECTION('+');

    private final char pathChar;

    PathType(char c) {
        this.pathChar = c;
    }

    public static PathType fromType(char c) {
        return Arrays.stream(PathType.values()).filter(pt -> pt.pathChar == c).findFirst().orElseThrow(RuntimeException::new);
    }

    public void print() {
        System.out.print(pathChar);
    }
}
