package day10;

import java.util.Objects;

public class Position implements Comparable<Position> {
    public final int x;
    public final int y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int compareTo(Position other) {
        int result = this.y - other.y;
        if(result == 0) {
            return this.x - other.x;
        } else {
            return result;
        }
    }
}
