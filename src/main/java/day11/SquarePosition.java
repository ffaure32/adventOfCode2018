package day11;

import java.util.Objects;

public class SquarePosition {
    public final int x;
    public final int y;
    public final int size;

    public SquarePosition(int x, int y, int size) {

        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquarePosition that = (SquarePosition) o;
        return x == that.x &&
                y == that.y &&
                size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, size);
    }

    @Override
    public String toString() {
        return "SquarePosition{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}
