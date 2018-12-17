package day17;

public enum GroundType {
    SPRING('+'),
    SAND('.'),
    CLAY('#');

    public final char charType;

    GroundType(char charType) {
        this.charType = charType;
    }

    @Override
    public String toString() {
        return String.valueOf(charType);
    }
}
