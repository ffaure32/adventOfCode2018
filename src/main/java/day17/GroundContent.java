package day17;

public enum GroundContent {
    EMPTY('.'),
    FLOW('|'),
    REST('~');

    private final char contentChar;

    GroundContent(char c) {
        contentChar = c;
    }

    @Override
    public String toString() {
        return String.valueOf(contentChar);
    }
}
